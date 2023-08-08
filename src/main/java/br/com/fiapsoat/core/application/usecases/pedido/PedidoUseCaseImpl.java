package br.com.fiapsoat.core.application.usecases.pedido;

import br.com.fiapsoat.adapters.dto.CheckoutPedidoDTO;
import br.com.fiapsoat.adapters.dto.PedidoDTO;
import br.com.fiapsoat.core.application.usecases.cliente.ClienteUseCase;
import br.com.fiapsoat.core.domain.entities.cliente.Cliente;
import br.com.fiapsoat.core.domain.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.core.domain.entities.pedido.Pedido;
import br.com.fiapsoat.core.domain.entities.produto.Produto;
import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import br.com.fiapsoat.core.domain.mappers.ProdutoMapper;
import br.com.fiapsoat.core.domain.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.fiapsoat.core.domain.entities.enums.StatusDoPagamento.AGUARDANDO_PAGAMENTO;

@Service
@AllArgsConstructor
public class PedidoUseCaseImpl implements PedidoUseCase {

    private final PedidoRepository repository;
    private final ClienteUseCase clienteUseCase;
    private final ProdutoMapper produtoMapper;

    @Override
    public PedidoDTO buscarPedidoPorId(Long id) {

        Optional<Pedido> optional = findById(id);

        if (optional.isEmpty()) {
            throw BusinessException
                    .builder()
                    .erro("Pedido não encontrado")
                    .detalhes(List.of(MessageFormat.format("Pedido não encontrado para o id {0}", id)))
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }

        return pedidoDTOBuilder(optional.get());

    }

    private PedidoDTO pedidoDTOBuilder(Pedido pedido) {

        Double total = pedido.getProdutos().stream().map(Produto::getPreco).reduce(0.0, Double::sum);

        Long tempoDesdeORecebimentoDoPedido = Duration.between(pedido.getPedidoRealizadoEm(), LocalDateTime.now()).toMinutes();

        return PedidoDTO
                .builder()
                .numeroDoPedido(pedido.getId())
                .pedidoRealizadoEm(pedido.getPedidoRealizadoEm())
                .pedidoRetiradoEm(pedido.getPedidoRetirado())
                .etapa(pedido.getEtapa())
                .statusDoPagamento(pedido.getStatusDoPagamento())
                .tempoDesdeRecebimentoDoPedido(MessageFormat.format("{0} minutos", tempoDesdeORecebimentoDoPedido))
                .cliente(pedido.getCliente() == null ? "Cliente não identificado" : pedido.getCliente().getNome().getValue())
                .valorTotalDoPedido(MessageFormat.format("R$ {0}", BigDecimal.valueOf(total).setScale(2)))
                .items(produtoMapper.entityToDto(pedido.getProdutos()))
                .build();
    }

    @Override
    public PedidoDTO checkoutPedido(CheckoutPedidoDTO dto) {

        Cliente cliente = null;

        if (StringUtils.isNotBlank(dto.getCpf())) {
            cliente = new Cliente(clienteUseCase.buscarClientePorCpf(dto.getCpf()));
        }

        if (CollectionUtils.isEmpty(dto.getProdutos())){
            throw BusinessException
                    .builder()
                    .erro("Nenhum produto selecionado")
                    .detalhes(List.of("Ao menos um produto deve ser adicionado a lista de produtos para prosseguir com o checkout do pedido"))
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }

        List<Produto> produtos = produtoMapper.dtoToEntity(dto.getProdutos());

        Pedido entity = repository.save(new Pedido(cliente, produtos));

        return pedidoDTOBuilder(entity);

    }

    @Override
    @Transactional
    public void atualizarStatusPagamentoDoPedido(Long pedido) {
        Optional<Pedido> optional = findById(pedido);
        optional.get().setStatusDoPagamento(StatusDoPagamento.PAGO);
    }

    private Optional<Pedido> findById(Long pedido) {
        return repository.findById(pedido);
    }

    @Override
    public List<PedidoDTO> listar() {
        return repository.buscarPedidosDisponiveis(StatusDoPagamento.PAGO).stream().map(this::pedidoDTOBuilder).toList();
    }

    @Transactional
    public PedidoDTO atualizaParaAProximaEtapaDoPedido(Long pedido) {

        Optional<Pedido> optional = findById(pedido);
        Pedido entity = optional.get();

        if(entity.getStatusDoPagamento() == AGUARDANDO_PAGAMENTO){
            throw BusinessException
                    .builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .erro("Não foi possível atualizar a etapa do pedido")
                    .detalhes(List.of(MessageFormat.format("O pedido {0} encontra-se com o status de pagamento {1}. Só será possível atualizar a etapa de pedido com o pagamento confirmado.", pedido, entity.getStatusDoPagamento().getStatus())))
                    .build();
        }

        entity.setEtapa(entity.getEtapa().proximaEtapa());

        return pedidoDTOBuilder(entity);

    }

}

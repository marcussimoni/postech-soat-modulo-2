package br.com.fiapsoat.usecases.pedido;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.services.pedido.PedidoService;
import br.com.fiapsoat.usecases.cliente.ClienteUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;

import static br.com.fiapsoat.entities.enums.StatusDoPagamento.AGUARDANDO_PAGAMENTO;

@Service
@AllArgsConstructor
public class PedidoUseCaseImpl implements PedidoUseCase {

    private final PedidoService pedidoService;
    private final ClienteUseCase clienteUseCase;

    @Override
    public Pedido buscarPedidoPorId(Long id) {

        return pedidoService.findById(id);

    }

    @Override
    public Pedido checkoutPedido(Cpf cpf, List<Produto> produtos) {

        Cliente cliente = null;

        if (StringUtils.isNotBlank(cpf.getValue())) {
            cliente = clienteUseCase.buscarClientePorCpf(cpf.getValue());
        }

        if (CollectionUtils.isEmpty(produtos)){
            throw new BusinessException("Nenhum produto selecionado", List.of("Ao menos um produto deve ser adicionado a lista de produtos para prosseguir com o checkout do pedido"), HttpStatus.BAD_REQUEST);
        }

        return pedidoService.save(new Pedido(cliente, produtos));

    }

    @Override
    @Transactional
    public void atualizarStatusPagamentoDoPedido(Long id, StatusDoPagamento status) {
        Pedido pedido = pedidoService.findById(id);
        pedido.setStatusDoPagamento(status);
        pedidoService.save(pedido);
    }


    @Override
    public List<Pedido> listar(StatusDoPagamento statusDoPagamento) {

        if(statusDoPagamento == null) {
            return pedidoService.buscarTodosOsPedidosDisponiveis();
        } else {
            return pedidoService.buscarPedidosDisponiveis(statusDoPagamento);
        }

    }

    @Transactional
    public Pedido atualizaParaAProximaEtapaDoPedido(Long pedido) {

        Pedido entity = pedidoService.findById(pedido);

        if(entity.getStatusDoPagamento() == AGUARDANDO_PAGAMENTO){
            String mensagem = "Não foi possível atualizar a etapa do pedido";
            List<String> detalhes = List.of(MessageFormat.format("O pedido {0} encontra-se com o status de pagamento {1}. Só será possível atualizar a etapa de pedido com o pagamento confirmado.", pedido, entity.getStatusDoPagamento().getStatus()));
            throw new BusinessException(mensagem, detalhes, HttpStatus.BAD_REQUEST);
        }

        entity.setEtapa(entity.getEtapa().proximaEtapa());

        return pedidoService.save(entity);

    }

}

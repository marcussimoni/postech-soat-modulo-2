package br.com.fiapsoat.db.pedido;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.gateways.pedido.PedidoGateway;
import br.com.fiapsoat.db.produto.TbProduto;
import br.com.fiapsoat.gateways.cliente.ClienteGateway;
import br.com.fiapsoat.gateways.produto.ProdutoGateway;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

@Repository
@AllArgsConstructor
public class PedidoGatewayImpl implements PedidoGateway {

    private final SpringRepository repository;
    private final ProdutoGateway produtoGateway;
    private final ClienteGateway clienteGateway;

    @Override
    public List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento) {
        return repository.buscarPedidosDisponiveis(statusDoPagamento).stream().map(this::pedidoBuilder).toList();
    }

    @Override
    public Pedido save(Pedido pedido) {

        TbPedido pedidoSalvo = repository.save(new TbPedido(pedido));

        return pedidoBuilder(pedidoSalvo);

    }

    @Override
    public Pedido findById(Long idDoPedido) {

        TbPedido pedido = repository.findById(idDoPedido).orElseThrow(() -> {
            throw new BusinessException("Pedido não encontrado", List.of(MessageFormat.format("Pedido não encontrado para o id {0}", idDoPedido)), HttpStatus.BAD_REQUEST);
        });

        return pedidoBuilder(pedido);
    }

    private Pedido pedidoBuilder(TbPedido pedido){
        Pedido p = new Pedido();
                p.setId(pedido.getId());
                p.setPedidoRealizadoEm(pedido.getPedidoRealizadoEm());
                p.setPedidoRetirado(pedido.getPedidoRetirado());
                p.setEtapa(pedido.getEtapa());
                p.setStatusDoPagamento(pedido.getStatusDoPagamento());
                p.setCliente(pedido.getCliente().clienteBuilder());
                p.setProdutos(pedido.getProdutos().stream().map(TbProduto::produtoBuilder).toList());
        return p;
    }

    private interface SpringRepository extends JpaRepository<TbPedido, Long> {
        @Query("SELECT p FROM TbPedido p WHERE p.pedidoRetirado IS NULL AND p.statusDoPagamento = :statusDoPagamento ORDER BY p.pedidoRealizadoEm ASC")
        List<TbPedido> buscarPedidosDisponiveis(@Param("statusDoPagamento") StatusDoPagamento statusDoPagamento);
    }

}

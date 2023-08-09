package br.com.fiapsoat.presenters.pedido;

import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.presenters.dto.PedidoDTO;
import br.com.fiapsoat.presenters.dto.ProdutoDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class PedidoPresenter {

    public PedidoDTO pedidoDTOBuilder(Pedido pedido) {

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
                .items(pedido.getProdutos().stream().map(ProdutoDTO::new).toList())
                .build();
    }

}

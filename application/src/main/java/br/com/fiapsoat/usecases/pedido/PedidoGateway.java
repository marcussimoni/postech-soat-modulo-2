package br.com.fiapsoat.usecases.pedido;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;

import java.util.List;

public interface PedidoGateway {
    List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento);

    Pedido save(Pedido pedido);

    Pedido findById(Long pedido);
}

package br.com.fiapsoat.services.pedido;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido findById(Long id);

    Pedido save(Pedido pedido);

    List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento);
    List<Pedido> buscarTodosOsPedidosDisponiveis();
}

package br.com.fiapsoat.core.domain.repositories;

import br.com.fiapsoat.core.domain.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.core.domain.entities.pedido.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento);

    Pedido save(Pedido pedido);

    Optional<Pedido> findById(Long pedido);
}

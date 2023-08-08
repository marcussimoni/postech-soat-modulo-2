package br.com.fiapsoat.core.application.usecases.pedido;

import br.com.fiapsoat.adapters.dto.CheckoutPedidoDTO;
import br.com.fiapsoat.adapters.dto.PedidoDTO;
import br.com.fiapsoat.core.domain.entities.enums.EtapaDoPedido;

import java.util.List;

public interface PedidoUseCase {
    PedidoDTO buscarPedidoPorId(Long id);

    PedidoDTO checkoutPedido(CheckoutPedidoDTO dto);

    void atualizarStatusPagamentoDoPedido(Long pedido);

    List<PedidoDTO> listar();

    PedidoDTO atualizaParaAProximaEtapaDoPedido(Long pedido);
}

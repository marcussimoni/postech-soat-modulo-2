package br.com.fiapsoat.usecases.pedido;

import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.presenters.dto.CheckoutPedidoDTO;

import java.util.List;

public interface PedidoUseCase {
    Pedido buscarPedidoPorId(Long id);

    Pedido checkoutPedido(Cpf cpf, List<Produto> produtos);

    void atualizarStatusPagamentoDoPedido(Long pedido);

    List<Pedido> listar();

    Pedido atualizaParaAProximaEtapaDoPedido(Long pedido);
}

package br.com.fiapsoat.controller;

import br.com.fiapsoat.presenters.dto.PedidoDTO;

import java.util.List;

public interface PedidoController {

    List<PedidoDTO> listar();

    PedidoDTO atualizaParaEmPreparacao(Long pedido);

    PedidoDTO buscarPedidoPorId(Long id);

}

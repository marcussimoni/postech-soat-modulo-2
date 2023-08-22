package br.com.fiapsoat.controller;

import br.com.fiapsoat.presenters.dto.ComprovanteDTO;

public interface PagamentoController {

    void confirmacaoPagamento(Long idPagamento);
    ComprovanteDTO buscarComprovante(Long idPagamento);

}

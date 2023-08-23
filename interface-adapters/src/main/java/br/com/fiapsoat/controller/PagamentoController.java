package br.com.fiapsoat.controller;

import br.com.fiapsoat.presenters.dto.ComprovanteDTO;
import br.com.fiapsoat.presenters.dto.ConfirmacaoPagamentoDTO;
import br.com.fiapsoat.presenters.dto.PagamentoDTO;
import br.com.fiapsoat.presenters.dto.PagamentoPedidoDTO;

public interface PagamentoController {

    void confirmacaoPagamento(ConfirmacaoPagamentoDTO confirmacaoPagamentoDTO);

    PagamentoDTO consultarStatusDoPagamento(Long idPagamento);
    ComprovanteDTO buscarComprovante(Long idPagamento);

    ComprovanteDTO pagamento(PagamentoPedidoDTO pagamentoPedidoDTO);
}

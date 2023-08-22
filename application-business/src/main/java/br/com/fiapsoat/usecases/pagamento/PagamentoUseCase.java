package br.com.fiapsoat.usecases.pagamento;

import br.com.fiapsoat.entities.recibo.Comprovante;

public interface PagamentoUseCase {
    Comprovante pagamento(Long pedido);
    void confirmacaoPagamento(Long idPagamento);
    Comprovante buscarComprovante(Long idPagamento);


}

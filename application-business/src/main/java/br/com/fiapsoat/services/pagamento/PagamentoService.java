package br.com.fiapsoat.services.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;

public interface PagamentoService {
    Pagamento registrarPagamento(Pagamento pagamento);

    Pagamento buscarPagamentoPorId(Long idPagamento);

    void atualizarStatusPagamento(Pagamento pagamento, StatusDoPagamento statusDoPagamento);
}

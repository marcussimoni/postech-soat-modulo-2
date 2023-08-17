package br.com.fiapsoat.usecases.pagamento;

import br.com.fiapsoat.entities.recibo.Recibo;

public interface PagamentoUseCase {
    Recibo pagamento(Long pedido);

}

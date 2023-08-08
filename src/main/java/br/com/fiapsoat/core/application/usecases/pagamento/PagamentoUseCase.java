package br.com.fiapsoat.core.application.usecases.pagamento;

import br.com.fiapsoat.adapters.dto.ReciboDTO;

public interface PagamentoUseCase {
    ReciboDTO pagamento(Long pedido);

}

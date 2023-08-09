package br.com.fiapsoat.usecases.pagamento;

import br.com.fiapsoat.presenters.dto.ReciboDTO;

public interface PagamentoUseCase {
    ReciboDTO pagamento(Long pedido);

}

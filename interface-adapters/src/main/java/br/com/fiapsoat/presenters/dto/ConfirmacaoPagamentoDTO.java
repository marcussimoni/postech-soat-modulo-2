package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.ConfirmacaoPagamento;
import lombok.Data;

@Data
public class ConfirmacaoPagamentoDTO {

    private Long idPagamento;
    private StatusDoPagamento statusDoPagamento;

    public ConfirmacaoPagamento toConfirmacaoPagamento() {
        ConfirmacaoPagamento c = new ConfirmacaoPagamento();
        c.setIdPagamento(getIdPagamento());
        c.setStatusDoPagamento(getStatusDoPagamento());
        return c;
    }
}

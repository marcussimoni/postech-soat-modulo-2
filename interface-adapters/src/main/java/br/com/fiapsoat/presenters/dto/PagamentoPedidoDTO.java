package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.pagamento.PagamentoPedido;
import lombok.Data;

@Data
public class PagamentoPedidoDTO {
    private Long idDoPedido;

    public PagamentoPedido toPagamentoPedido() {
        return new PagamentoPedido(this.getIdDoPedido());
    }
}

package br.com.fiapsoat.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusDoPagamento {
    AGUARDANDO_PAGAMENTO("Aguardando pagamento"), PAGO("Pago");

    private final String status;

    StatusDoPagamento(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus(){
        return this.status;
    }

}

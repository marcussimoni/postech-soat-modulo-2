package br.com.fiapsoat.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum StatusDoPagamento {
    AGUARDANDO_PAGAMENTO("Aguardando pagamento"), AGUARDANDO_CONFIRMACAO("Aguardando confirmação do pagamento pela instituição financeira"), PAGO("Pagamento confirmado");

    private final String status;

    StatusDoPagamento(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

}

package br.com.fiapsoat.entities.recibo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
public class Recibo {
    private String codigoDeAutenticacao;
    private Long codigoDoPedido;

    private LocalDateTime dataHoraPagamento;
}

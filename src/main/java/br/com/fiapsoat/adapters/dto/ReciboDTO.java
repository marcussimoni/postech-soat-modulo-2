package br.com.fiapsoat.adapters.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReciboDTO {

    private String codigoDeAutenticacao;
    private Long codigoDoPedido;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraPagamento;

}

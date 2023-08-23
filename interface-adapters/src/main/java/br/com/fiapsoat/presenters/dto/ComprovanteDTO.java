package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.recibo.Comprovante;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComprovanteDTO {

    private String nomeDoCliente;
    private long codigoDoPagamento;
    private long codigoDoPedido;

    private String codigoDeAutenticacao;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraPagamento;

    private List<String> itensPedido;
    private String valorTotal;

}

package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.recibo.Comprovante;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
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

    public ComprovanteDTO(Comprovante comprovante) {
        this.codigoDoPagamento = comprovante.getIdDoPagamento();
        this.codigoDoPedido = comprovante.getIdDoPedido();
        this.dataHoraPagamento = comprovante.getDataHoraPagamento();
        this.codigoDeAutenticacao = comprovante.getCodigoDeAutenticacao();
        this.itensPedido = comprovante.getItensPedido();
        this.valorTotal = comprovante.getValorTotal();
        this.nomeDoCliente = comprovante.getNomeDoCliente();
    }

}

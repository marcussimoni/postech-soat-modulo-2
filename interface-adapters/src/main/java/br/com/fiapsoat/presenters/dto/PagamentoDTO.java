package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PagamentoDTO {

    private Long id;
    private Long codigoDoPedido;
    private String codigoDeAutenticacao;
    private LocalDateTime dataDoPagamento;
    private LocalDateTime dataDeConfirmacao;
    private String status;

}

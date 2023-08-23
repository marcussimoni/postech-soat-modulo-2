package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.controller.PagamentoController;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.recibo.Comprovante;
import br.com.fiapsoat.presenters.pagamento.PagamentoPresenter;
import br.com.fiapsoat.presenters.dto.ComprovanteDTO;
import br.com.fiapsoat.presenters.dto.ConfirmacaoPagamentoDTO;
import br.com.fiapsoat.presenters.dto.PagamentoDTO;
import br.com.fiapsoat.presenters.dto.PagamentoPedidoDTO;
import br.com.fiapsoat.usecases.pagamento.PagamentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("pagamento")
@Tag(name = "Área administrativa - Pagamentos", description = "Gerencia os pagamentos para os pedidos realizados")
public class PagamentoControllerImpl implements PagamentoController {

    private final PagamentoUseCase pagamentoUserCase;
    private final PagamentoPresenter pagamentoPresenter;

    @Override
    @PutMapping(path = "/webhook")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Área administrativa - Pagamentos", summary = "Webhook", description = "Webhook para confirmação utilizado pelo gateway de pagamento")
    public void confirmacaoPagamento(
            @RequestBody ConfirmacaoPagamentoDTO confirmacaoPagamentoDTO
    ) {
        pagamentoUserCase.confirmacaoPagamento(confirmacaoPagamentoDTO.toConfirmacaoPagamento());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(tags = "Área administrativa - Pagamentos", summary = "Consultar status do pagamento", description = "Consulta status do pagamento para os pedidos realizados")
    public PagamentoDTO consultarStatusDoPagamento(Long idPagamento) {
        Pagamento pagamento = pagamentoUserCase.buscarPagamento(idPagamento);
        return pagamentoPresenter.toPagamentoDTO(pagamento);
    }

    @Override
    @GetMapping(path = "/{id}/comprovante")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Área administrativa - Pagamentos", summary = "Comprovante", description = "Comprovante da confirmação de pagamento do pedido")
    public ComprovanteDTO buscarComprovante(@Parameter(description = "Id do pagamento", name = "id") @PathVariable("id") Long idPagamento) {
        return pagamentoPresenter.toComprovanteDTO(pagamentoUserCase.buscarComprovante(idPagamento));
    }

    @Override
    @PostMapping
    @Operation(tags = "Área administrativa - Pagamentos", summary = "Pagamento do pedido", description = "Realiza o pagamento do pedido para que o pedido possa ser encaminhado para o preparo. Caso o pagamento não seja concluído o pedido não poderá avançar para as próximas etapas.")
    public ComprovanteDTO pagamento(
            @RequestBody PagamentoPedidoDTO pagamentoPedidoDTO
            ){
        Comprovante comprovante = pagamentoUserCase.pagamento(pagamentoPedidoDTO.toPagamentoPedido());
        return pagamentoPresenter.toComprovanteDTO(comprovante);
    }
}

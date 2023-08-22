package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.controller.PagamentoController;
import br.com.fiapsoat.presenters.dto.ComprovanteDTO;
import br.com.fiapsoat.usecases.pagamento.PagamentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
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

    @Override
    @PutMapping(path = "/webhook/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Área administrativa - Pagamentos", summary = "Webhook", description = "Webhook para confirmação utilizado pelo gateway de pagamento")
    public void confirmacaoPagamento(@PathVariable("idDoPagamento") Long idPagamento) {
        pagamentoUserCase.confirmacaoPagamento(idPagamento);
    }

    @Override
    @GetMapping(path = "/{id}/comprovante")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Área administrativa - Pagamentos", summary = "Comprovante", description = "Comprovante da confirmação de pagamento do pedido")

    public ComprovanteDTO buscarComprovante(@PathVariable("idDoPagamento") Long idPagamento) {
        return new ComprovanteDTO(pagamentoUserCase.buscarComprovante(idPagamento));
    }
}

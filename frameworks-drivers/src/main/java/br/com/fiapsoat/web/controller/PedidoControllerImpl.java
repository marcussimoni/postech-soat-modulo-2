package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.presenters.dto.PedidoDTO;
import br.com.fiapsoat.presenters.pedido.PedidoPresenter;
import br.com.fiapsoat.usecases.pedido.PedidoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("pedido")
@Tag(name = "Pedidos", description = "Apresenta as opções utilizadas pela equipe responsável pelo preparo dos pedidos")
public class PedidoControllerImpl {

    private final PedidoUseCase pedidoUseCase;
    private final PedidoPresenter pedidoPresenter;

    @GetMapping
    @Operation(tags = "Pedidos", summary = "Lista todos os pedidos disponíveis.", description = "Lista todos os pedidos nas etapas pago, recebido, em preparação ou pronto. Para produtos finalizados utilizar a busca por número do pedido.")
    public List<PedidoDTO> listar(@RequestParam(value = "status", required = false) StatusDoPagamento statusDoPagamento){
        return pedidoUseCase.listar(statusDoPagamento).stream().map(pedidoPresenter::pedidoDTOBuilder).toList();
    }

    @PutMapping(path = "/proxima-etapa/{pedido}")
    @Operation(tags = "Pedidos", summary = "Atualiza etapa do pedido para próxima etapa disponível", description = "As etapas são atualizadas somente após o pagamento confirmado. As etapas são atualizadas seguindo a ordem: RECEBIDO para EM PREPARACAO para PRONTO e FINALIZADO")
    public PedidoDTO atualizaParaEmPreparacao(@PathVariable Long pedido){
        return pedidoPresenter.pedidoDTOBuilder(pedidoUseCase.atualizaParaAProximaEtapaDoPedido(pedido));
    }

    @GetMapping(path = "/buscar-por-numero")
    @Operation(tags = "Pedidos", summary = "Buscar pedido por número do pedido", description = "Consulta o pedido pelo número do pedido")
    public PedidoDTO buscarPedidoPorId(@Parameter(name = "id", description = "Número do pedido") @RequestParam(name = "id") Long id){
        return pedidoPresenter.pedidoDTOBuilder(pedidoUseCase.buscarPedidoPorId(id));
    }

}

package br.com.fiapsoat.adapters.driver.controller;

import br.com.fiapsoat.adapters.dto.PedidoDTO;
import br.com.fiapsoat.adapters.dto.ProdutoDTO;
import br.com.fiapsoat.core.application.usecases.pedido.PedidoUseCase;
import br.com.fiapsoat.core.application.usecases.produto.ProdutoUseCase;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;
import br.com.fiapsoat.core.domain.entities.enums.EtapaDoPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("pedido")
@Tag(name = "Pedidos", description = "Apresenta as opções utilizadas pela equipe responsável pelo preparo dos pedidos")
public class PedidoController {

    private final PedidoUseCase pedidoUseCase;

    @GetMapping
    @Operation(tags = "Pedidos", summary = "Lista todos os pedidos disponíveis.", description = "Lista todos os pedidos nas etapas pago, recebido, em preparação ou pronto. Para produtos finalizados utilizar a busca por número do pedido.")
    public List<PedidoDTO> listar(){
        return pedidoUseCase.listar();
    }

    @PutMapping(path = "/proxima-etapa/{pedido}")
    @Operation(tags = "Pedidos", summary = "Atualiza etapa do pedido para próxima etapa disponível", description = "As etapas são atualizadas somente após o pagamento confirmado. As etapas são atualizadas seguindo a ordem: RECEBIDO para EM PREPARACAO para PRONTO e FINALIZADO")
    public PedidoDTO atualizaParaEmPreparacao(@PathVariable Long pedido){
        return pedidoUseCase.atualizaParaAProximaEtapaDoPedido(pedido);
    }

    @GetMapping(path = "/buscar-por-numero")
    @Operation(tags = "Pedidos", summary = "Buscar pedido por número do pedido", description = "Consulta o pedido pelo número do pedido")
    public PedidoDTO buscarPedidoPorId(@Parameter(name = "id", description = "Número do pedido") @RequestParam(name = "id") Long id){
        return pedidoUseCase.buscarPedidoPorId(id);
    }

}

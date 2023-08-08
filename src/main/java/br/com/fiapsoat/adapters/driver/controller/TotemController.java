package br.com.fiapsoat.adapters.driver.controller;

import br.com.fiapsoat.adapters.dto.*;
import br.com.fiapsoat.core.application.usecases.cliente.ClienteUseCase;
import br.com.fiapsoat.core.application.usecases.pagamento.PagamentoUseCase;
import br.com.fiapsoat.core.application.usecases.pedido.PedidoUseCase;
import br.com.fiapsoat.core.application.usecases.produto.ProdutoUseCase;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;
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
@RequestMapping("atendimento")
@AllArgsConstructor
@Tag(description = "Apresenta etapas para o cliente realizar o pedido", name = "Totem de auto atendimento")
public class TotemController {

    private final ClienteUseCase clienteUseCase;
    private final ProdutoUseCase produtoUseCase;
    private final PedidoUseCase pedidoUseCase;
    private final PagamentoUseCase pagamentoUseCase;

    @GetMapping(path = "/cliente/buscar")
    @Operation(summary = "Buscar cliente por cpf", tags = "Totem de auto atendimento", description = "Consulta na base de dados um cliente cadastrado a partir do cpf")
    public ClienteDTO buscarClientePorCpf(
            @Parameter(description = "Cpf do cliente para identificação")
            @RequestHeader String cpf
    ) {
        return clienteUseCase.buscarClientePorCpf(cpf);
    }

    @PostMapping(path = "/cliente/cadastrar")
    @Operation(summary = "Cadastrar novo cliente", tags = "Totem de auto atendimento", description = "Cadastrar um novo cliente caso ainda não seja cadastrado na base de dados. Não pode existir mais de um cliente com o mesmo cpf")
    public ClienteDTO cadastrarNovoCliente(
            @Parameter(description = "Dados do cliente")
            @RequestBody ClienteDTO clienteDTO
    ) {
        return clienteUseCase.salvar(clienteDTO);
    }

    @GetMapping(path = "/produtos")
    @Operation(tags = "Totem de auto atendimento", summary = "Buscar produtos por categoria", description = "Lista todos os produtos disponíveis para o pedido ordenados por categoria.")
    public List<ProdutoDTO> buscarProdutosPorCategoria(
            @RequestParam(name = "categoria", required = false) Categoria categoria){
        return produtoUseCase.listar(categoria);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/pedido/checkout")
    @Operation(tags = "Totem de auto atendimento", summary = "Checkout do pedido", description = "Realiza o checkout do pedido com todos os itens selecionados e os dados do cliente caso o mesmo tenha se identificado. É obrigatório a adicionar ao menos um produto a lista para prosseguir com o checkout do pedido.")
    public PedidoDTO checkout(@RequestBody CheckoutPedidoDTO dto){
        return pedidoUseCase.checkoutPedido(dto);
    }

    @PostMapping(path = "/pedido/pagamento/{pedido}")
    @Operation(tags = "Totem de auto atendimento", summary = "Pagamento do pedido", description = "Realiza o pagamento do pedido para que o pedido possa ser encaminhado para o preparo. Caso o pagamento não seja concluído o pedido não poderá avançar para as próximas etapas.")
    public ReciboDTO pagamento(@Parameter(name = "pedido", description = "Código do pedido gerado pelo sistema") @PathVariable(name = "pedido") Long pedido){
        return pagamentoUseCase.pagamento(pedido);
    }

}

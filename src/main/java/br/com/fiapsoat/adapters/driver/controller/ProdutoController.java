package br.com.fiapsoat.adapters.driver.controller;

import br.com.fiapsoat.adapters.dto.ProdutoDTO;
import br.com.fiapsoat.core.application.usecases.produto.ProdutoUseCase;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("produto")
@Tag(name = "Área administrativa - Produtos", description = "Gerencia os produtos disponíveis para pedidos")
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;

    @GetMapping
    @Operation(tags = "Área administrativa - Produtos", summary = "Listar produtos", description = "Lista todos os produtos disponíveis")
    public List<ProdutoDTO> listar(
            @RequestParam(name = "categoria", required = false) Categoria categoria){
        return produtoUseCase.listar(categoria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "Área administrativa - Produtos", description = "Cadastra um novo produto", summary = "Cadastrar novo produto")
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produto) {
        return produtoUseCase.salvar(produto);
    }


    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "Área administrativa - Produtos", description = "Atualizar um produto", summary = "Atualizar produto")
    public ProdutoDTO atualizar(@RequestBody ProdutoDTO produto, @PathVariable Long id) {
        produto.setId(id);
        return produtoUseCase.atualizar(produto);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(tags = "Área administrativa - Produtos", description = "Excluir um produto", summary = "Excluir produto")
    public void excluir(@PathVariable Long id) {
        produtoUseCase.excluir(id);
    }

}

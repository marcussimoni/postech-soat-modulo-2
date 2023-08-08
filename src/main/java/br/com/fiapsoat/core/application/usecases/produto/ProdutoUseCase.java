package br.com.fiapsoat.core.application.usecases.produto;

import br.com.fiapsoat.adapters.dto.ProdutoDTO;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;

import java.util.List;

public interface ProdutoUseCase {
    List<ProdutoDTO> listar(Categoria categoria);
    ProdutoDTO salvar(ProdutoDTO produto);

    ProdutoDTO atualizar(ProdutoDTO produto);

    void excluir(Long id);
}

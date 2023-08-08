package br.com.fiapsoat.core.domain.repositories;

import br.com.fiapsoat.adapters.dto.ProdutoDTO;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;
import br.com.fiapsoat.core.domain.entities.produto.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    List<ProdutoDTO> findByCategoria(Categoria categoria);

    Produto save(Produto entity);

    void delete(Produto produto);

    Optional<Produto> findById(Long id);
}

package br.com.fiapsoat.adapters.driven.repositories;

import br.com.fiapsoat.adapters.dto.ProdutoDTO;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;
import br.com.fiapsoat.core.domain.entities.produto.Produto;
import br.com.fiapsoat.core.domain.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final SpringRepository repository;

    @Override
    public List<ProdutoDTO> findByCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    @Override
    public Produto save(Produto entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Produto produto) {
        repository.delete(produto);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return repository.findById(id);
    }

    private interface SpringRepository extends JpaRepository<Produto, Long> {

        @Query("SELECT NEW br.com.fiapsoat.adapters.dto.ProdutoDTO(p) FROM Produto p " +
                "WHERE (p.categoria = :categoria OR :categoria IS NULL)")
        List<ProdutoDTO> findByCategoria(@Param("categoria") Categoria categoria);
    }
}

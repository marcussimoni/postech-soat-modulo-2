package br.com.fiapsoat.gateways.impl.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.gateways.ProdutoGateway;
import br.com.fiapsoat.presenters.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final SpringRepository repository;

    @Override
    public List<Produto> findByCategoria(Categoria categoria) {
        List<TbProduto> produtos = repository.findByCategoria(categoria);
        return produtos.stream().map(this::produtoBuilder).toList();
    }

    @Override
    public Produto save(Produto entity) {
        TbProduto produto = new TbProduto(entity);
        TbProduto produtoSalvo = repository.save(produto);
        return produtoBuilder(produtoSalvo);
    }

    @Override
    public void delete(Produto produto) {
        repository.delete(new TbProduto(produto));
    }

    @Override
    public Produto findById(Long id) {
        TbProduto produto = repository.findById(id).orElseThrow(() -> {
            throw new BusinessException("Produto não encontrado para o id " + id, List.of("Produto não encontrado para o id " + id), HttpStatus.NOT_FOUND);
        });

        return produtoBuilder(produto);
    }

    @Override
    public Produto produtoBuilder(TbProduto produto){
        return Produto
                .builder()
                .id(produto.getId())
                .preco(produto.getPreco())
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .imagem(produto.getImagem())
                .nome(produto.getNome())
                .build();
    }

    private interface SpringRepository extends JpaRepository<TbProduto, Long> {

        @Query("SELECT p FROM TbProduto p " +
                "WHERE (p.categoria = :categoria OR :categoria IS NULL)")
        List<TbProduto> findByCategoria(@Param("categoria") Categoria categoria);
    }
}

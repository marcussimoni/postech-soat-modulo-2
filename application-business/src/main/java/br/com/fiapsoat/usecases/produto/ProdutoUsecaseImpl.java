package br.com.fiapsoat.usecases.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.services.produto.ProdutoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoUsecaseImpl implements ProdutoUseCase {

    private final ProdutoService produtoService;

    @Override
    public List<Produto> listar(Categoria categoria) {
        return produtoService.findByCategoria(categoria);
    }

    @Override
    @Transactional
    public Produto salvar(Produto produto) {

        return produtoService.save(produto);

    }

    @Override
    @Transactional
    public Produto atualizar(Produto produto) {

        findById(produto.getId());

        return produtoService.save(produto);

    }

    @Override
    public void excluir(Long id) {
        Produto produto = findById(id);
        produtoService.delete(produto);
    }

    private Produto findById(Long id) {
        return produtoService.findById(id);
    }
}

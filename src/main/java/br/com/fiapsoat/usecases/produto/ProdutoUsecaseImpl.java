package br.com.fiapsoat.usecases.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.gateways.ProdutoGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoUsecaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public List<Produto> listar(Categoria categoria) {
        return produtoGateway.findByCategoria(categoria);
    }

    @Override
    @Transactional
    public Produto salvar(Produto produto) {

        return produtoGateway.save(produto);

    }

    @Override
    @Transactional
    public Produto atualizar(Produto produto) {

        findById(produto.getId());

        return produtoGateway.save(produto);

    }

    @Override
    public void excluir(Long id) {
        Produto produto = findById(id);
        produtoGateway.delete(produto);
    }

    private Produto findById(Long id) {
        return produtoGateway.findById(id);
    }
}

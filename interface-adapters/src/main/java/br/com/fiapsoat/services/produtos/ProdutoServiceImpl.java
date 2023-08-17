package br.com.fiapsoat.services.produtos;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.gateways.produto.ProdutoGateway;
import br.com.fiapsoat.services.produto.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoGateway produtoGateway;

    @Override
    public List<Produto> findByCategoria(Categoria categoria) {
        return produtoGateway.findByCategoria(categoria);
    }

    @Override
    public Produto save(Produto produto) {
        return produtoGateway.save(produto);
    }

    @Override
    public void delete(Produto produto) {
        produtoGateway.delete(produto);
    }

    @Override
    public Produto findById(Long id) {
        return produtoGateway.findById(id);
    }
}

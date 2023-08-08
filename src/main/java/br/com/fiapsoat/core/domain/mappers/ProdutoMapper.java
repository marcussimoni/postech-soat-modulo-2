package br.com.fiapsoat.core.domain.mappers;

import br.com.fiapsoat.core.domain.entities.produto.Produto;
import br.com.fiapsoat.adapters.dto.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper extends Mapper<Produto, ProdutoDTO> {

    public ProdutoMapper() {
        super(new ModelMapper());
    }

}

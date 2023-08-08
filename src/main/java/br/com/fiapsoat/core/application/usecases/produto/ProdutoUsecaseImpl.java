package br.com.fiapsoat.core.application.usecases.produto;

import br.com.fiapsoat.adapters.dto.ProdutoDTO;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;
import br.com.fiapsoat.core.domain.entities.produto.Produto;
import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import br.com.fiapsoat.core.domain.mappers.ProdutoMapper;
import br.com.fiapsoat.core.domain.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoUsecaseImpl implements ProdutoUseCase {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    @Override
    public List<ProdutoDTO> listar(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    @Override
    @Transactional
    public ProdutoDTO salvar(ProdutoDTO dto) {

        dto.setId(null);

        Produto entity = mapper.dtoToEntity(dto);

        Produto saved = repository.save(entity);

        return mapper.entityToDto(saved);

    }

    @Override
    @Transactional
    public ProdutoDTO atualizar(ProdutoDTO dto) {

        findById(dto.getId());

        Produto produto = mapper.dtoToEntity(dto);

        repository.save(produto);

        return dto;

    }

    @Override
    public void excluir(Long id) {
        Optional<Produto> optional = findById(id);
        repository.delete(optional.get());
    }

    private Optional<Produto> findById(Long id) {
        Optional<Produto> optional = repository.findById(id);

        if(optional.isEmpty()){
            throw BusinessException
                    .builder()
                    .statusCode(HttpStatus.NOT_FOUND)
                    .erro("Produto não encontrado para o id " + id)
                    .detalhes(List.of("Produto não encontrado para o id " + id))
                    .build();
        }

        return optional;
    }
}

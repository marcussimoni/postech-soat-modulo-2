package br.com.fiapsoat.core.application.usecases.cliente;

import br.com.fiapsoat.adapters.dto.ClienteDTO;
import br.com.fiapsoat.core.domain.entities.cliente.Cliente;
import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import br.com.fiapsoat.core.domain.mappers.ClienteMapper;
import br.com.fiapsoat.core.domain.repositories.ClienteRepository;
import br.com.fiapsoat.core.domain.valueobjects.cpf.Cpf;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Override
    @Transactional
    public ClienteDTO salvar(ClienteDTO dto) {

        Optional<Cliente> optional = repository.findByCpf(new Cpf(dto.getCpf()));

        if(optional.isPresent()){

            throw BusinessException
                    .builder()
                    .erro("Cliente já cadastrado")
                    .detalhes(List.of(MessageFormat.format("Já existe um cliente cadastrado para o cpf {0}", dto.getCpf())))
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();

        } else {

            Cliente entity = new Cliente(dto);
            entity.setId(null);
            Cliente saved = repository.save(entity);

            return mapper.entityToDto(saved);

        }

    }

    @Override
    public List<ClienteDTO> listar() {
        return IteratorUtils
                .toList(repository.findAll().iterator())
                .stream()
                .map(obj -> {
                    ClienteDTO clienteDTO = new ClienteDTO();
                    clienteDTO.setId(obj.getId());
                    clienteDTO.setNome(obj.getNome().getValue());
                    clienteDTO.setCpf(obj.getCpf().format());
                    clienteDTO.setEmail(obj.getEmail().getEmail());
                    return clienteDTO;
                }).collect(Collectors.toList())
                ;
    }

    @Override
    public ClienteDTO buscarClientePorCpf(String cpf) {

        Cpf cpfValueObject = new Cpf(cpf);
        Optional<Cliente> optional = repository.findByCpf(cpfValueObject);

        if (optional.isPresent()){
            return mapper.entityToDto(optional.get());
        } else {
            throw BusinessException
                    .builder()
                    .erro("Cliente não encontrado")
                    .detalhes(List.of("Cliente não encontrado para o cpf " + cpfValueObject.format()))
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
        }

    }
}

package br.com.fiapsoat.core.domain.mappers;

import br.com.fiapsoat.adapters.dto.ClienteDTO;
import br.com.fiapsoat.core.domain.entities.cliente.Cliente;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper extends Mapper<Cliente, ClienteDTO> {

    public ClienteMapper() {
        super(new ModelMapper());
    }

    @PostConstruct
    public void configMapper() {
        mapper.addMappings(new PropertyMap<Cliente, ClienteDTO>() {

            @Override
            protected void configure() {
                map().setCpf(source.getCpf().format());
                map().setEmail(source.getEmail().getEmail());
                map().setNome(source.getNome().getValue());
            }

        });

    }

}

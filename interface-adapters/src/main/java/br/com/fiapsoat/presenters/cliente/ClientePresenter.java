package br.com.fiapsoat.presenters.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.presenters.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClientePresenter {

    public List<ClienteDTO> toClienteDTO(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO toClienteDTO(Cliente clienteSalvo) {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteSalvo.getId());
        clienteDTO.setNome(clienteSalvo.getNome().getValue());
        clienteDTO.setCpf(clienteSalvo.getCpf().format());
        clienteDTO.setEmail(clienteSalvo.getEmail().getValue());

        return clienteDTO;

    }
}

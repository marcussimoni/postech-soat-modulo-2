package br.com.fiapsoat.core.application.usecases.cliente;

import br.com.fiapsoat.adapters.dto.ClienteDTO;

import java.util.List;

public interface ClienteUseCase {

    ClienteDTO salvar(ClienteDTO dto);
    List<ClienteDTO> listar();

    ClienteDTO buscarClientePorCpf(String cpf);
}

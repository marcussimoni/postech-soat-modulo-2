package br.com.fiapsoat.services.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;

import java.util.List;

public interface ClienteService {
    Cliente findByCpf(Cpf cpf);

    Cliente save(Cliente cliente);

    List<Cliente> findAll();
}

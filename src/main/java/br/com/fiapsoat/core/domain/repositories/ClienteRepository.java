package br.com.fiapsoat.core.domain.repositories;

import br.com.fiapsoat.core.domain.entities.cliente.Cliente;
import br.com.fiapsoat.core.domain.valueobjects.cpf.Cpf;

import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findByCpf(Cpf cpf);
    Cliente save(Cliente cliente);

    Iterable<Cliente> findAll();
}

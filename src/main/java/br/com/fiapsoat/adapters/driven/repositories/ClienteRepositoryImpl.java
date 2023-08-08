package br.com.fiapsoat.adapters.driven.repositories;

import br.com.fiapsoat.core.domain.entities.cliente.Cliente;
import br.com.fiapsoat.core.domain.repositories.ClienteRepository;
import br.com.fiapsoat.core.domain.valueobjects.cpf.Cpf;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClienteRepositoryImpl implements ClienteRepository {

    private final SpringRepository repository;

    @Override
    public Optional<Cliente> findByCpf(Cpf cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Iterable<Cliente> findAll() {
        return repository.findAll();
    }

    @Repository
    private interface SpringRepository extends CrudRepository<Cliente, Long> {
        Optional<Cliente> findByCpf(Cpf cpf);
    }

}

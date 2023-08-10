package br.com.fiapsoat.usecases.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;

import java.util.List;

public interface ClienteGateway {
    Cliente findByCpf(Cpf cpf);
    Cliente save(Cliente cliente);
    List<Cliente> findAll();

}

package br.com.fiapsoat.gateways;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.gateways.impl.cliente.TbCliente;

import java.util.List;

public interface ClienteGateway {
    Cliente findByCpf(Cpf cpf);
    Cliente save(Cliente cliente);
    List<Cliente> findAll();

    Cliente clienteBuilder(TbCliente cliente);
}

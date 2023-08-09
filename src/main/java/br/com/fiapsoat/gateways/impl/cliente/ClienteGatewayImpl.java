package br.com.fiapsoat.gateways.impl.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.email.Email;
import br.com.fiapsoat.entities.valueobjects.nome.Nome;
import br.com.fiapsoat.gateways.ClienteGateway;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.presenters.exceptions.BusinessException;
import br.com.fiapsoat.presenters.exceptions.ClientNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final SpringRepository repository;

    @Override
    public Cliente findByCpf(Cpf cpf) {

        TbCliente cliente = repository.findByCpf(cpf.getValue()).orElseThrow(() -> {
            throw new ClientNotFoundException("Cliente já cadastrado", List.of(MessageFormat.format("Já existe um cliente cadastrado para o cpf {0}", cpf.getValue())), HttpStatus.BAD_REQUEST);
        });

        return clienteBuilder(cliente);

    }

    @Override
    public Cliente save(Cliente cliente) {

        TbCliente tbCliente = new TbCliente(cliente);

        TbCliente clienteSalvo = repository.save(tbCliente);

        return clienteBuilder(clienteSalvo);

    }

    @Override
    public List<Cliente> findAll() {
        Iterator<TbCliente> clientes = repository.findAll().iterator();
        return IteratorUtils
                .toList(clientes)
                .stream()
                .map(this::clienteBuilder)
                .toList();
    }

    @Override
    public Cliente clienteBuilder(TbCliente cliente) {
        return Cliente
                .builder()
                .id(cliente.getId())
                .cpf(new Cpf(cliente.getCpf()))
                .email(new Email(cliente.getEmail()))
                .nome(new Nome(cliente.getNome()))
                .build();
    }

    @Repository
    private interface SpringRepository extends CrudRepository<TbCliente, Long> {
        Optional<TbCliente> findByCpf(String cpf);
    }

}

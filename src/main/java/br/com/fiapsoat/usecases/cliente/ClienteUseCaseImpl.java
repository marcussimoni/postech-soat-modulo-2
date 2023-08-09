package br.com.fiapsoat.usecases.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.gateways.ClienteGateway;
import br.com.fiapsoat.presenters.exceptions.BusinessException;
import br.com.fiapsoat.presenters.exceptions.ClientNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {

        try {
            return clienteGateway.findByCpf(cliente.getCpf());
        } catch (ClientNotFoundException e) {
            cliente.setId(null);
            return clienteGateway.save(cliente);
        }

    }

    @Override
    public List<Cliente> listar() {
        return clienteGateway.findAll();
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {

        Cpf cpfValueObject = new Cpf(cpf);
        return clienteGateway.findByCpf(cpfValueObject);

    }
}

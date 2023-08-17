package br.com.fiapsoat.usecases.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.exceptions.ClientNotFoundException;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.services.cliente.ClienteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteService clienteService;

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {

        try {
            return clienteService.findByCpf(cliente.getCpf());
        } catch (ClientNotFoundException e) {
            cliente.setId(null);
            return clienteService.save(cliente);
        }

    }

    @Override
    public List<Cliente> listar() {
        return clienteService.findAll();
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {

        Cpf cpfValueObject = new Cpf(cpf);
        return clienteService.findByCpf(cpfValueObject);

    }
}

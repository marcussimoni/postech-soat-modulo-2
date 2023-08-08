package br.com.fiapsoat.adapters.driver.controller;

import br.com.fiapsoat.adapters.dto.ClienteDTO;
import br.com.fiapsoat.core.application.usecases.cliente.ClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("cliente")
@Tag(name = "Área administrativa - Clientes", description = "Gerencia os dados do cliente cadastrados para campanhas promocionais")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    @GetMapping
    @Operation(tags = "Área administrativa - Clientes", summary = "Listar clientes", description = "Lista todos os clientes cadastrados")
    public List<ClienteDTO> listar(){
        return clienteUseCase.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "Área administrativa - Clientes", summary = "Cadastrar cliente", description = "Salva um novo cliente")
    public ClienteDTO salvar(@RequestBody ClienteDTO cliente) {
        return clienteUseCase.salvar(cliente);
    }

}

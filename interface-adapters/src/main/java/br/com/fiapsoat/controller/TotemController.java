package br.com.fiapsoat.controller;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.recibo.Recibo;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.entities.valueobjects.email.Email;
import br.com.fiapsoat.entities.valueobjects.nome.Nome;
import br.com.fiapsoat.presenters.cliente.ClientePresenter;
import br.com.fiapsoat.presenters.dto.*;
import br.com.fiapsoat.presenters.pedido.PedidoPresenter;
import br.com.fiapsoat.presenters.produto.ProdutoPresenter;
import br.com.fiapsoat.usecases.cliente.ClienteUseCase;
import br.com.fiapsoat.usecases.pagamento.PagamentoUseCase;
import br.com.fiapsoat.usecases.pedido.PedidoUseCase;
import br.com.fiapsoat.usecases.produto.ProdutoUseCase;
import br.com.fiapsoat.entities.enums.Categoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TotemController {

    ClienteDTO buscarClientePorCpf(String cpf);

    ClienteDTO cadastrarNovoCliente(ClienteDTO dto);

    List<ProdutoDTO> buscarProdutosPorCategoria(Categoria categoria);

    PedidoDTO checkout(CheckoutPedidoDTO dto);

    ReciboDTO pagamento(Long pedido);

}

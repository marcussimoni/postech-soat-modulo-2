package br.com.fiapsoat.presenters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutPedidoDTO {

    @Schema(description = "Cpf do cliente caso tenha se cadastrado no sistema", example = "111.111.111-11")
    private String cpf;

    @Schema(description = "Lista de produtos selecionados para o pedido. Deve conter ao menos um produto para prosseguir")
    private List<ProdutoDTO> produtos;

}

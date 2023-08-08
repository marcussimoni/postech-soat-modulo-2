package br.com.fiapsoat.core.application.usecases.pagamento;

import br.com.fiapsoat.adapters.dto.ReciboDTO;
import br.com.fiapsoat.core.application.usecases.pedido.PedidoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PagamentoUseCaseImpl implements PagamentoUseCase {

    private PedidoUseCase pedidoUseCase;

    @Override
    public ReciboDTO pagamento(Long pedido) {

        pedidoUseCase.atualizarStatusPagamentoDoPedido(pedido);

        return ReciboDTO
                .builder()
                .dataHoraPagamento(LocalDateTime.now())
                .codigoDeAutenticacao(UUID.randomUUID().toString())
                .codigoDoPedido(pedido)
                .build();
    }

}

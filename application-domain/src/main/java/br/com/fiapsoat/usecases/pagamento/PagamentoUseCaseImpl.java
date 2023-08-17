package br.com.fiapsoat.usecases.pagamento;

import br.com.fiapsoat.entities.recibo.Recibo;
import br.com.fiapsoat.usecases.pedido.PedidoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PagamentoUseCaseImpl implements PagamentoUseCase {

    private PedidoUseCase pedidoUseCase;

    @Override
    public Recibo pagamento(Long pedido) {

        pedidoUseCase.atualizarStatusPagamentoDoPedido(pedido);

        return Recibo
                .builder()
                .dataHoraPagamento(LocalDateTime.now())
                .codigoDeAutenticacao(UUID.randomUUID().toString())
                .codigoDoPedido(pedido)
                .build();
    }

}

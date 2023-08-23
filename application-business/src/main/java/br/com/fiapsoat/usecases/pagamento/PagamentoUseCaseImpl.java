package br.com.fiapsoat.usecases.pagamento;

import br.com.fiapsoat.entities.pagamento.ConfirmacaoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.pagamento.PagamentoPedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.recibo.Comprovante;
import br.com.fiapsoat.services.pagamento.PagamentoService;
import br.com.fiapsoat.usecases.pedido.PedidoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.fiapsoat.entities.enums.StatusDoPagamento.AGUARDANDO_CONFIRMACAO;

@Service
@AllArgsConstructor
public class PagamentoUseCaseImpl implements PagamentoUseCase {

    private PedidoUseCase pedidoUseCase;
    private PagamentoService pagamentoService;

    @Override
    public Comprovante pagamento(PagamentoPedido pagamentoPedido) {

        Pagamento pagamento = new Pagamento(pedidoUseCase.buscarPedidoPorId(pagamentoPedido.getNumeroDoPedido()));

        Pagamento pagamentoRegistrado = pagamentoService.registrarPagamento(pagamento);

        pedidoUseCase.atualizarStatusPagamentoDoPedido(pagamentoPedido.getNumeroDoPedido(), AGUARDANDO_CONFIRMACAO);

        return comprovanteBuilder(pagamentoRegistrado);

    }

    @Override
    public void confirmacaoPagamento(ConfirmacaoPagamento confirmacaoPagamento) {

        Pagamento pagamento = pagamentoService.buscarPagamentoPorNumeroDoPedido(confirmacaoPagamento.getNumeroDoPedido());

        pagamentoService.atualizarStatusPagamento(pagamento, confirmacaoPagamento.getStatusDoPagamento());

        pedidoUseCase.atualizarStatusPagamentoDoPedido(pagamento.getPedido().getId(), confirmacaoPagamento.getStatusDoPagamento());

    }

    @Override
    public Comprovante buscarComprovante(Long numeroDoPedido) {
        return comprovanteBuilder(pagamentoService.buscarPagamentoPorNumeroDoPedido(numeroDoPedido));
    }

    @Override
    public Pagamento buscarPagamentoPorNumeroDoPedido(Long numeroDoPedido) {
        return pagamentoService.buscarPagamentoPorNumeroDoPedido(numeroDoPedido);
    }

    private String calcularValorTotalDoPedido(Pagamento pagamentoRegistrado) {
        Double total = pagamentoRegistrado.getPedido().getProdutos().stream().map(Produto::getPreco).reduce(0.0, Double::sum);
        return MessageFormat.format("R$ {0}", BigDecimal.valueOf(total).setScale(2));
    }

    private List<String> itensPedido(Pagamento pagamento) {
        return pagamento.getPedido().getProdutos().stream().map(produto -> {
            return MessageFormat.format("Item: {0} valor: R$ {1}", produto.getNome(), BigDecimal.valueOf(produto.getPreco()).setScale(2));
        }).collect(Collectors.toList());
    }

    private Comprovante comprovanteBuilder(Pagamento pagamentoRegistrado) {

        String totalDoPedido = calcularValorTotalDoPedido(pagamentoRegistrado);
        String nomeDoCliente = "Cliente não identificado";

        if(pagamentoRegistrado.getPedido().getCliente() != null) {
            nomeDoCliente = pagamentoRegistrado.getPedido().getCliente().getNome().getValue();
        }

        return Comprovante
                .builder()
                .idDoPagamento(pagamentoRegistrado.getId())
                .numeroDoPedido(pagamentoRegistrado.getPedido().getId())
                .dataHoraPagamento(pagamentoRegistrado.getDataDoPagamento())
                .codigoDeAutenticacao(pagamentoRegistrado.getCodigoDeAutenticacao())
                .codigoDoPedido(pagamentoRegistrado.getId())
                .itensPedido(itensPedido(pagamentoRegistrado))
                .valorTotal(totalDoPedido)
                .nomeDoCliente(nomeDoCliente)
                .build();
    }

}

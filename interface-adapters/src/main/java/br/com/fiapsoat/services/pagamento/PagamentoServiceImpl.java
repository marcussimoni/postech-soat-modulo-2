package br.com.fiapsoat.services.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.gateways.pagamento.PagamentoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoGateway pagamentoGateway;

    @Override
    public Pagamento registrarPagamento(Pagamento pagamento) {
        return pagamentoGateway.registrarPagamento(pagamento);
    }

    @Override
    public Pagamento buscarPagamentoPorId(Long idPagamento) {
        return pagamentoGateway.buscarPagamentoPorId(idPagamento);
    }

    @Override
    public void atualizarStatusPagamento(Pagamento pagamento, StatusDoPagamento statusDoPagamento) {
        pagamentoGateway.atualizarStatusPagamento(pagamento, statusDoPagamento);
    }

}

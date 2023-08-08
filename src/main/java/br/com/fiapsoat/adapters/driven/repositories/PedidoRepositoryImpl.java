package br.com.fiapsoat.adapters.driven.repositories;

import br.com.fiapsoat.core.domain.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.core.domain.entities.pedido.Pedido;
import br.com.fiapsoat.core.domain.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

    private final SpringRepository repository;

    @Override
    public List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento) {
        return repository.buscarPedidosDisponiveis(statusDoPagamento);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    @Override
    public Optional<Pedido> findById(Long pedido) {
        return repository.findById(pedido);
    }

    private interface SpringRepository extends JpaRepository<Pedido, Long> {
        @Query("SELECT p FROM Pedido p WHERE p.pedidoRetirado IS NULL AND p.statusDoPagamento = :statusDoPagamento ORDER BY p.pedidoRealizadoEm ASC")
        List<Pedido> buscarPedidosDisponiveis(@Param("statusDoPagamento") StatusDoPagamento statusDoPagamento);
    }

}

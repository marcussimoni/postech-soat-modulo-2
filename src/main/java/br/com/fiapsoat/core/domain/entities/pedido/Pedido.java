package br.com.fiapsoat.core.domain.entities.pedido;

import br.com.fiapsoat.core.domain.entities.cliente.Cliente;
import br.com.fiapsoat.core.domain.entities.enums.EtapaDoPedido;
import br.com.fiapsoat.core.domain.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.core.domain.entities.produto.Produto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiapsoat.core.domain.entities.enums.EtapaDoPedido.FINALIZADO;
import static br.com.fiapsoat.core.domain.entities.enums.EtapaDoPedido.RECEBIDO;
import static br.com.fiapsoat.core.domain.entities.enums.StatusDoPagamento.AGUARDANDO_PAGAMENTO;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pedido")
public class Pedido implements Serializable {

    public Pedido(Cliente cliente, List<Produto> produtos) {
        setPedidoRealizadoEm(LocalDateTime.now());
        setEtapa(RECEBIDO);
        setStatusDoPagamento(AGUARDANDO_PAGAMENTO);
        setProdutos(produtos);
        setCliente(cliente);
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private EtapaDoPedido etapa;

    @Column(name = "status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusDoPagamento statusDoPagamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pedido_realizado_em")
    private LocalDateTime pedidoRealizadoEm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pedido_retirado_em")
    private LocalDateTime pedidoRetirado;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    @PreUpdate
    private void atualizarCampoPedidoRetirado(){
        if(etapa == FINALIZADO && pedidoRetirado == null){
            this.pedidoRetirado = LocalDateTime.now();
        }
    }

}

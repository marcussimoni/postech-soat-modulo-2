package br.com.fiapsoat.gateways.impl.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cliente")
public class TbCliente implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    public TbCliente(Cliente c){
        this.id = c.getId();
        this.nome = c.getNome().getValue();
        this.cpf = c.getCpf().getValue();
        this.email = c.getEmail().getValue();
    }

}

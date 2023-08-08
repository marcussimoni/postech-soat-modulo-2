package br.com.fiapsoat.core.domain.entities.cliente;

import br.com.fiapsoat.adapters.dto.ClienteDTO;
import br.com.fiapsoat.core.domain.valueobjects.cpf.Cpf;
import br.com.fiapsoat.core.domain.valueobjects.email.Email;
import br.com.fiapsoat.core.domain.valueobjects.cpf.CpfAttributeConverter;
import br.com.fiapsoat.core.domain.valueobjects.email.EmailAttributeConverter;
import br.com.fiapsoat.core.domain.valueobjects.nome.Nome;
import br.com.fiapsoat.core.domain.valueobjects.nome.NomeAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = NomeAttributeConverter.class)
    private Nome nome;

    @Convert(converter = CpfAttributeConverter.class)
    private Cpf cpf;

    @Convert(converter = EmailAttributeConverter.class)
    private Email email;

    public Cliente(ClienteDTO dto) {
        setId(dto.getId());
        setNome(new Nome(dto.getNome()));
        setEmail(new Email(dto.getEmail()));
        setCpf(new Cpf(dto.getCpf()));
    }
}

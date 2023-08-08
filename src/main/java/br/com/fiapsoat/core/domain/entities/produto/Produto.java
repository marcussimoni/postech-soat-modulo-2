package br.com.fiapsoat.core.domain.entities.produto;

import br.com.fiapsoat.core.domain.entities.EntityValidation;
import br.com.fiapsoat.core.domain.entities.enums.Categoria;
import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.io.Serializable;

@Data
@Entity
@Table(name = "produto")
@SQLDelete(sql = "update produto set ativo = false where id = ?")
@Where(clause = "ativo = true")
public class Produto implements Serializable, EntityValidation<Produto> {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "O campo nome não pode ser vazio")
    @Size(min = 0, max = 100, message = "O Campo nome deve conter até 100 caracteres")
    private String nome;

    @Column(name = "descricao")
    @NotBlank(message = "O campo descrição não pode ser vazio")
    @Size(min = 0, max = 300, message = "O Campo descrição deve conter até 300 caracteres")
    private String descricao;

    @Column(name = "preco")
    @NotNull(message = "O campo preço é obrigatório")
    @DecimalMin(value = "0.0", message = "O preço não pode ser negativo")
    private Double preco;

    @Column
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "O campo categoria não pode ser vazio")
    private Categoria categoria;

    @Column(name = "imagem")
    @NotBlank(message = "O campo imagem não pode ser vazio")
    private String imagem;

    @Override
    @PreUpdate
    @PrePersist
    public void validate() throws BusinessException {
        validate("Dados do produto inválidos", this);
    }

}

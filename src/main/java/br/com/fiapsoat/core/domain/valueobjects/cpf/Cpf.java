package br.com.fiapsoat.core.domain.valueobjects.cpf;

import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Cpf {

    private String cpf;

    public Cpf(String cpf){
        this.cpf = cpf;
        validate(cpf);
    }

    private void validate(String cpf){
        String value = cpf.replaceAll("\\D", "");
        if(value.length() < 11){
            throw BusinessException
                    .builder()
                    .erro("Cpf inválido")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }
        this.cpf = value;
    }

    public String format(){
        return getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}

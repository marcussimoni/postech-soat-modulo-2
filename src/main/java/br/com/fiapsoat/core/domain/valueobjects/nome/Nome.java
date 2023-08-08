package br.com.fiapsoat.core.domain.valueobjects.nome;

import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class Nome {

    private final String value;

    public Nome(String nome){
        this.value = nome;
        validate(this.value);
    }

    private void validate(String value) {

        if(StringUtils.isBlank(value)){
            throw BusinessException
                    .builder()
                    .erro("Nome do cliente inválido")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }

    }

    public String getValue(){
        return this.value;
    }

}

package br.com.fiapsoat.core.domain.valueobjects.email;

import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

@Getter
public class Email {

    private final String email;

    public Email(String email){
        this.email = email;
        validate(email);
    }

    private void validate(String email){

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-zA-Z]");

        if(StringUtils.isBlank(email) || !pattern.matcher(email).find()){
            throw BusinessException
                    .builder()
                    .erro("E-mail inválido")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }

    }

}

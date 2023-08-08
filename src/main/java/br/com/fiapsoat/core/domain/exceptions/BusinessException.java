package br.com.fiapsoat.core.domain.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class BusinessException extends RuntimeException {

    private String erro;
    private List<String> detalhes;
    private HttpStatus statusCode;

}

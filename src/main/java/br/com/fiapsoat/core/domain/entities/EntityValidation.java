package br.com.fiapsoat.core.domain.entities;

import br.com.fiapsoat.core.domain.exceptions.BusinessException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface EntityValidation<Entity> {

    void validate() throws BusinessException;

    default void validate(String mensagem, Entity entity) throws BusinessException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<Entity>> validate = validatorFactory.getValidator().validate(entity);
        if (!validate.isEmpty()){
            List<String> detalhes = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw BusinessException
                    .builder()
                    .erro(mensagem)
                    .detalhes(detalhes)
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}

package br.com.fiapsoat.entities;

import br.com.fiapsoat.presenters.exceptions.BusinessException;
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
            throw new BusinessException(mensagem, detalhes, HttpStatus.BAD_REQUEST);
        }
    }
}

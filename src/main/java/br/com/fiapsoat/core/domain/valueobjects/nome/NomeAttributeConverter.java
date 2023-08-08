package br.com.fiapsoat.core.domain.valueobjects.nome;

import br.com.fiapsoat.core.domain.valueobjects.email.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NomeAttributeConverter implements AttributeConverter<Nome, String> {
    @Override
    public String convertToDatabaseColumn(Nome nome) {
        return nome.getValue();
    }

    @Override
    public Nome convertToEntityAttribute(String value) {
        return new Nome(value);
    }

}

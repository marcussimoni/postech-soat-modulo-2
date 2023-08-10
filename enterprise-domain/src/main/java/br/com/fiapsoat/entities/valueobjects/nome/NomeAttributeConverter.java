package br.com.fiapsoat.entities.valueobjects.nome;

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

package br.com.fiapsoat.core.domain.valueobjects.email;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmailAttributeConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email.getEmail();
    }

    @Override
    public Email convertToEntityAttribute(String value) {
        return new Email(value);
    }
}

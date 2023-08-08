package br.com.fiapsoat.core.domain.valueobjects.cpf;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CpfAttributeConverter implements AttributeConverter<Cpf, String> {

    @Override
    public String convertToDatabaseColumn(Cpf cpf) {
        return cpf.getCpf();
    }

    @Override
    public Cpf convertToEntityAttribute(String value) {
        return new Cpf(value);
    }

}

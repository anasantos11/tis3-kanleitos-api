package br.com.kanleitos.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusRegistroConverter implements AttributeConverter<StatusRegistro, String> {

	@Override
	public String convertToDatabaseColumn(StatusRegistro statusRegistro) {
		return statusRegistro.getNome();
	}

	@Override
	public StatusRegistro convertToEntityAttribute(String dbData) {
		return StatusRegistro.fromName(dbData);
	}
}
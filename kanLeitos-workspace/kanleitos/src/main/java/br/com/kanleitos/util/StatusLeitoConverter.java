package br.com.kanleitos.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusLeitoConverter implements AttributeConverter<TipoStatusLeito, String> {

	@Override
	public String convertToDatabaseColumn(TipoStatusLeito status) {
		return status.getNome();
	}

	@Override
	public TipoStatusLeito convertToEntityAttribute(String dbData) {
		return TipoStatusLeito.fromName(dbData);
	}
}
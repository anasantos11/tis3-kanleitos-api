package br.com.kanleitos.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ClassificacaoConverter implements AttributeConverter<Classificacao, String> {

	@Override
	public String convertToDatabaseColumn(Classificacao classificacao) {
		return classificacao.getNome();
	}

	@Override
	public Classificacao convertToEntityAttribute(String dbData) {
		return Classificacao.fromName(dbData);
	}
}
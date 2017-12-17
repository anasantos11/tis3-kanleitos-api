package br.com.kanleitos.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, String> {

	@Override
	public String convertToDatabaseColumn(StatusPedido statusPedido) {
		return statusPedido.getNome();
	}

	@Override
	public StatusPedido convertToEntityAttribute(String dbData) {
		return StatusPedido.fromName(dbData);
	}
}
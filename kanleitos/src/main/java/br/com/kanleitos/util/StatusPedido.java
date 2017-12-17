package br.com.kanleitos.util;

public enum StatusPedido {
	PENDENTE("Pendente"), CONCLUIDO("Concluido"), CANCELADO("Cancelado"), ATRASADO ("Atrasado");

	private String nome;

	private StatusPedido(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static StatusPedido fromName(String statusPedido) {
		switch (statusPedido) {
		case "Pendente":
			return StatusPedido.PENDENTE;
		case "Concluido":
			return StatusPedido.CONCLUIDO;
		case "Cancelado":
			return StatusPedido.CANCELADO;
		case "Atrasado":
			return StatusPedido.ATRASADO;
		default:
			throw new IllegalArgumentException("Unknown" + statusPedido);
		}
	}

}

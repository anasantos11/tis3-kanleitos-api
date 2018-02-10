package br.com.kanleitos.util;

public enum StatusRegistro {
	EM_ANDAMENTO("Em Andamento"), ALTA("Alta"), OBITO("Óbito"), TRANSFERENCIA ("");

	private String nome;

	private StatusRegistro(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static StatusRegistro fromName(String statusRegistro) {
		switch (statusRegistro) {
		case "Em Andamento":
			return StatusRegistro.EM_ANDAMENTO;
		case "Alta":
			return StatusRegistro.ALTA;
		case "Óbito":
			return StatusRegistro.OBITO;
		case "Transferência":
			return StatusRegistro.TRANSFERENCIA;
		default:
			throw new IllegalArgumentException("Unknown" + statusRegistro);
		}
	}

}

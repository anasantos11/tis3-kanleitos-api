package br.com.kanleitos.util;

public enum Classificacao {
	VERDE("Verde"), AMARELO("Amarelo"), VERMELHO("Vermelho");

	private String nome;

	private Classificacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static Classificacao fromName(String classificacao) {
		switch (classificacao) {
		case "Verde":
			return Classificacao.VERDE;
		case "Amarelo":
			return Classificacao.AMARELO;
		case "Vermelho":
			return Classificacao.VERMELHO;
		default:
			throw new IllegalArgumentException("Unknown" + classificacao);
		}
	}

}

package br.com.kanleitos.util;

public enum TipoStatusLeito {
	DESOCUPADO("Desocupado"), OCUPADO_COMUM("Ocupado Comum"), OCUPADO_ISOLAMENTO_RESPIRATORIO(
			"Ocupado - Bloqueado Isolamento Respirat�rio"), OCUPADO_PRECAUCAO_CONTATO(
					"Ocupado - Bloqueado Precau��o Contato"), BLOQUEADO_REFORMA("Bloqueado Reforma"), INATIVO(
							"Inativo"), BLOQUEADO_RH("Bloqueado D�ficit RH"), AGUARDANDO_LIMPEZA("Aguardando Limpeza");

	private String nome;

	private TipoStatusLeito(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static TipoStatusLeito fromName(String status) {
		switch (status) {
		case "Desocupado":
			return TipoStatusLeito.DESOCUPADO;
		case "Ocupado Comum":
			return TipoStatusLeito.OCUPADO_COMUM;
		case "Ocupado - Bloqueado Isolamento Respirat�rio":
			return TipoStatusLeito.OCUPADO_ISOLAMENTO_RESPIRATORIO;
		case "Ocupado - Bloqueado Precau��o Contato":
			return TipoStatusLeito.OCUPADO_PRECAUCAO_CONTATO;
		case "Bloqueado Reforma":
			return TipoStatusLeito.BLOQUEADO_REFORMA;
		case "Inativo":
			return TipoStatusLeito.INATIVO;
		case "Bloqueado D�ficit RH":
			return TipoStatusLeito.BLOQUEADO_RH;
		case "Aguardando Limpeza":
			return TipoStatusLeito.AGUARDANDO_LIMPEZA;
		
		default:
			throw new IllegalArgumentException("Unknown" + status);
		}
	}

}

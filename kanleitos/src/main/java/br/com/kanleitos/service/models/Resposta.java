package br.com.kanleitos.service.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Resposta {

	private boolean erro;
	private List<String> mensagens;

	public static class RespostaKeys {
		public static final String ERRO = "erro";
		public static final String MENSAGENS = "mensagens";
	}

	public Resposta(boolean erro) {
		this(erro, new ArrayList<String>());
	}

	public Resposta(boolean erro, List<String> mensagens) {
		this.erro = erro;
		this.mensagens = mensagens;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put(RespostaKeys.ERRO, erro);
			json.put(RespostaKeys.MENSAGENS, mensagens);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

}

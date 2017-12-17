package br.com.kanleitos.util;

import org.json.JSONException;
import org.json.JSONObject;

public class Resposta {
	public Resposta() {
	}

	public static class RespostaKeys {
		public static final String ERRO = "erro";
		public static final String MENSAGEM = "mensagem";
	}
		
	public static String respostaToJson(boolean erro, Object dados) {
		JSONObject json = new JSONObject(dados);
		try {
			json.put(RespostaKeys.ERRO, erro);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

}

package br.com.kanleitos.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	public static String respostaToGson(Object dados) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(dados);
	}

}

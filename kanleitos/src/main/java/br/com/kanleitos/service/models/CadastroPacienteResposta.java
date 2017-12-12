package br.com.kanleitos.service.models;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroPacienteResposta extends Resposta {

	private String idPaciente;

	public static class CadastroPacienteRespostaKeys {
		public static final String ID_PACIENTE = "idPaciente";
	}

	public CadastroPacienteResposta(boolean erro) {
		super(erro);
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("Resposta", super.toJson());
			json.put(CadastroPacienteRespostaKeys.ID_PACIENTE, idPaciente);
		} catch (JSONException e) {
			ArrayList<String> mensagens = new ArrayList<String>();
			mensagens.add(e.toString());
			json = new Resposta(false, mensagens).toJson();
		}
		return json;
	}
}

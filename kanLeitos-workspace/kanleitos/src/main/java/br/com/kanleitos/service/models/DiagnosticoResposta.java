package br.com.kanleitos.service.models;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class DiagnosticoResposta extends Resposta {

	private String idDiagnostico;
	private String descricaoDiagnostico;
	private String CID;
	private String tempoPermanencia;

	public static class DiagnosticoRespostaKeys {
		private static final String DESCRICAO_DIAGNOSTICO = "descricaoDiagnostico";
		private static final String CID = "CID";
		private static final String TEMPO_PERMANENCIA = "tempoPermanencia";
	}

	public DiagnosticoResposta(boolean erro) {
		super(erro);
	}


	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("Resposta", super.toJson());
			json.put(DiagnosticoRespostaKeys.DESCRICAO_DIAGNOSTICO, descricaoDiagnostico);
			json.put(DiagnosticoRespostaKeys.CID, CID);
			json.put(DiagnosticoRespostaKeys.TEMPO_PERMANENCIA, tempoPermanencia);
			
		} catch (JSONException e) {
			ArrayList<String> mensagens = new ArrayList<String>();
			mensagens.add(e.toString());
			json = new Resposta(false, mensagens).toJson();
		}
		return json;
	}


	public String getIdDiagnostico() {
		return idDiagnostico;
	}


	public void setIdDiagnostico(String idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}


	public String getDescricaoDiagnostico() {
		return descricaoDiagnostico;
	}


	public void setDescricaoDiagnostico(String descricaoDiagnostico) {
		this.descricaoDiagnostico = descricaoDiagnostico;
	}


	public String getCID() {
		return CID;
	}


	public void setCID(String cID) {
		CID = cID;
	}


	public String getTempoPermanencia() {
		return tempoPermanencia;
	}


	public void setTempoPermanencia(String tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}
}

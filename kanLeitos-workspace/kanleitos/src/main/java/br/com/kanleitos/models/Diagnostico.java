package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Diagnostico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDiagnostico;

	@Column(name = "descricaoDiagnostico", nullable = false)
	private String descricaoDiagnostico;

	@Column(name = "CID", nullable = false)
	private String CID;

	@Column(name = "tempoPermanencia", nullable = false)
	private int tempoPermanencia;

	private static class Diagnosticokeys {

		private static final String DESCRICAO_DIAGNOSTICO = "descricaoDiagnostico";
		private static final String CID = "CID";
		private static final String TEMPO_PERMANENCIA = "tempoPermanencia";
	}
	
	public Diagnostico() {
		setCID("0000");
		setDescricaoDiagnostico(null);
		setTempoPermanencia(-1);
	}

	public Diagnostico(JSONObject json) throws JSONException {

		if (json.has(Diagnosticokeys.DESCRICAO_DIAGNOSTICO))
			setDescricaoDiagnostico(json.getString(Diagnosticokeys.DESCRICAO_DIAGNOSTICO));

		if (json.has(Diagnosticokeys.CID))
			setCID(json.getString(Diagnosticokeys.CID));

		if (json.has(Diagnosticokeys.TEMPO_PERMANENCIA))
			setTempoPermanencia(json.getInt(Diagnosticokeys.TEMPO_PERMANENCIA));
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

	public int getTempoPermanencia() {
		return tempoPermanencia;
	}

	public void setTempoPermanencia(int tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}

	public int getIdDiagnostico() {
		return idDiagnostico;
	}

}

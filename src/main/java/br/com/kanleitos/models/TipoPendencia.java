package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class TipoPendencia {

	@Id
	@SequenceGenerator(name="TIPO_PENDENCIA_ID",  initialValue=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="TIPO_PENDENCIA_ID")
	private long idPendencia;
	
	@Column(name = "descPendencia", nullable = false)
	private String descPendencia;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "registroInternacao",referencedColumnName = "idRegistroInternacao")
	private RegistroInternacao registroInternacao;
	
	public TipoPendencia() {
		setDescPendencia(null);
	}
	
	private static class PendenciaKeys {
		private static final String DESC_PENDENCIA = "descPendencia";
	}
	
	public TipoPendencia (JSONObject json) throws JSONException {
		if (json.has(PendenciaKeys.DESC_PENDENCIA))
			setDescPendencia(json.getString(PendenciaKeys.DESC_PENDENCIA));
	}
	public String getDescPendencia() {
		return descPendencia;
	}
	public void setDescPendencia(String descPendencia) {
		this.descPendencia = descPendencia;
	}
	public long getIdPendencia() {
		return idPendencia;
	}


}

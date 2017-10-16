package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class TipoExame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idExame;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exameLista",referencedColumnName = "idExameLista")
	private ExameLista exameLista;
	
	@Column(name = "descExame", nullable = false)
	private String descExame;
	
	@Column(name = "inativo", nullable = false)
	private boolean inativo;

	
	public TipoExame() {
		setDescExame(null);
		setInativo(false);
	}
	
	private static class ExameKeys {
		private static final String DESC_EXAME = "descExame";
		private static final String INATIVO = "inativo";
	}
	public TipoExame (JSONObject json) throws JSONException {

		if (json.has(ExameKeys.DESC_EXAME))
			setDescExame(json.getString(ExameKeys.DESC_EXAME));
		
		if (json.has(ExameKeys.INATIVO))
			setInativo(json.getBoolean(ExameKeys.INATIVO));
	}

	public String getDescExame() {
		return descExame;
	}

	public void setDescExame(String descExame) {
		this.descExame = descExame;
	}

	public boolean getInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public int getIdExame() {
		return idExame;
	}
	
	

}

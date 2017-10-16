package br.com.kanleitos.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.json.JSONException;
import org.json.JSONObject;

@Entity

public class Enfermaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEnfermaria;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAla", nullable = false)
	private Ala ala;
	
	@Column(name = "nomeEnfermaria", nullable = false)
	private String nomeEnfermaria;
	
	@Column(name = "generoEnfermaria")
	private String generoEnfermaria;

	@Column(name = "inativa", nullable = false)
	private boolean inativa;

	public Enfermaria() {
		setNomeEnfermaria(null);
		setGenero(null);
		setAla(new Ala());
		setInativa(false);
	}
	
	private static class Enfermariakeys {
		private static final String NOME_ENFERMARIA = "nomeEnfermaria";
		private static final String GENERO = "genero";
		private static final String ALA = "Ala";
		private static final String INATIVA = "inativa";

	}

	public Enfermaria(JSONObject json) throws JSONException {

		if (json.has(Enfermariakeys.NOME_ENFERMARIA))
			setNomeEnfermaria(json.getString(Enfermariakeys.NOME_ENFERMARIA));
		
		if (json.has(Enfermariakeys.GENERO))
			setGenero(json.getString(Enfermariakeys.GENERO));
		
		if (json.has(Enfermariakeys.ALA))
			setAla(json.getJSONObject(Enfermariakeys.ALA));
		
		if (json.has(Enfermariakeys.INATIVA))
			setInativa(json.getBoolean(Enfermariakeys.INATIVA));

	}

	public Ala getAla() {
		return ala;
	}

	public void setAla(Ala ala) {
		this.ala = ala;
	}

	public void setAla(JSONObject jsonObject) throws JSONException {
		this.ala = new Ala(jsonObject);
	}

	public String getNomeEnfermaria() {
		return nomeEnfermaria;
	}

	public void setNomeEnfermaria(String nomeEnfermaria) {
		this.nomeEnfermaria = nomeEnfermaria;
	}

	public String getGenero() {
		return generoEnfermaria;
	}

	public void setGenero(String generoEnfermaria) {
		this.generoEnfermaria = generoEnfermaria;
	}

	public boolean isInativa() {
		return inativa;
	}

	public void setInativa(boolean inativa) {
		this.inativa = inativa;
	}

	public int getIdEnfermaria() {
		return idEnfermaria;
	}

	

}

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

public class Leito {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idLeito;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAla", nullable = false)
	private Ala ala;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEnfermaria", nullable = false)
	private Enfermaria enfermaria;
	
	@Column(name = "nomeLeito", nullable = false)
	private String nomeLeito;
	
	@Column(name = "generoLeito", nullable = false)
	private String generoLeito;
	
	@Column(name = "tipoLeito", nullable = false)
	private String tipoLeito;
	
	@Column(name = "statusLeito", nullable = false)
	private String statusLeito;

	public Leito() {
		setNomeLeito(null);
		setGeneroLeito(null);
		setTipoLeito(null);
		setStatusLeito(null);
		setAla(new Ala());
		setEnfermaria(new Enfermaria());
	}
	
	private static class Leitokeys {
		private static final String NOME_LEITO = "nomeLeito";
		private static final String GENERO_LEITO = "generoLeito";
		private static final String TIPO_LEITO = "tipoLeito";
		private static final String STATUS_LEITO = "statusLeito";
		private static final String ALA = "Ala";
		private static final String ENFERMARIA = "Enfermaria";
	}

	public Leito(JSONObject json) throws JSONException {

		if (json.has(Leitokeys.NOME_LEITO))
			setNomeLeito(json.getString(Leitokeys.NOME_LEITO));
		
		if (json.has(Leitokeys.GENERO_LEITO))
			setGeneroLeito(json.getString(Leitokeys.GENERO_LEITO));
		
		if (json.has(Leitokeys.TIPO_LEITO))
			setTipoLeito(json.getString(Leitokeys.TIPO_LEITO));
		
		if (json.has(Leitokeys.STATUS_LEITO))
			setStatusLeito(json.getString(Leitokeys.STATUS_LEITO));
		
		if (json.has(Leitokeys.ALA))
			setAla(json.getJSONObject(Leitokeys.ALA));
		
		if (json.has(Leitokeys.ENFERMARIA))
			setEnfermaria(json.getJSONObject(Leitokeys.ENFERMARIA));

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

	public Enfermaria getEnfermaria() {
		return enfermaria;
	}
	
	public void setEnfermaria(Enfermaria enfermaria) {
		this.enfermaria = enfermaria;
	}
	
	
	public void setEnfermaria(JSONObject jsonObject) throws JSONException {
		this.enfermaria = new Enfermaria(jsonObject);
	}

	public String getNomeLeito() {
		return nomeLeito;
	}

	public void setNomeLeito(String nomeLeito) {
		this.nomeLeito = nomeLeito;
	}

	public String getGeneroLeito() {
		return generoLeito;
	}

	public void setGeneroLeito(String generoLeito) {
		this.generoLeito = generoLeito;
	}

	public String getTipoLeito() {
		return tipoLeito;
	}

	public void setTipoLeito(String tipoLeito) {
		this.tipoLeito = tipoLeito;
	}

	public String getStatusLeito() {
		return statusLeito;
	}

	public void setStatusLeito(String statusLeito) {
		this.statusLeito = statusLeito;
	}

	public int getIdLeito() {
		return idLeito;
	}


	

}

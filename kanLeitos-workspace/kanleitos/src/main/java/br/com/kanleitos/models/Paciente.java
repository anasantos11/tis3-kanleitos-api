package br.com.kanleitos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Entity implementation class for Entity: Pacientes
 *
 */
@Entity
@Table(name = "PACIENTE")

public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPaciente;
	
	private Integer numProntuario;
	private String nomePaciente;
	private Integer idade;
	private String genero;
	private String nomeMae;
	private String dataNascimento;
	
	private static class PacienteKeys {
		private static final String NUM_PRONTUARIO = "NumProntuario";
		private static final String NOME_PACIENTE = "NomePaciente";
		private static final String IDADE = "Idade";
		private static final String GENERO = "Genero";
		private static final String NOME_MAE = "NomeMae";
		private static final String DATA_NASCIMENTO = "DataNascimento";
	}
	public Paciente(JSONObject json) throws JSONException {
		

		if (json.has(PacienteKeys.NUM_PRONTUARIO))
			setNumProntuario(json.getInt(PacienteKeys.NUM_PRONTUARIO));
		
		if (json.has(PacienteKeys.NUM_PRONTUARIO))
			setNomePaciente(json.getString(PacienteKeys.NOME_PACIENTE));

		if (json.has(PacienteKeys.IDADE))
			setIdade(json.getInt(PacienteKeys.IDADE));
		
		if (json.has(PacienteKeys.GENERO))
			setGenero(json.getString(PacienteKeys.GENERO));
		
		if (json.has(PacienteKeys.NOME_MAE))
			setNomeMae(json.getString(PacienteKeys.NOME_MAE));
		
		if (json.has(PacienteKeys.DATA_NASCIMENTO))
			setDataNascimento(json.getString(PacienteKeys.DATA_NASCIMENTO));
			
		
	}
 
	public Paciente() {
		setDataNascimento(null);
		setGenero(null);
		setIdade(-1);
		setNomeMae(null);
		setNomePaciente(null);
		setNumProntuario(-1);
	}

	public Integer getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}   
	public Integer getNumProntuario() {
		return this.numProntuario;
	}

	public void setNumProntuario(Integer numProntuario) {
		this.numProntuario = numProntuario;
	}   
	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}   
	public Integer getIdade() {
		return this.idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}   
	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}   
	public String getNomeMae() {
		return this.nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}   
	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", numProntuario=" + numProntuario + ", nomePaciente="
				+ nomePaciente + ", idade=" + idade + ", genero=" + genero + ", nomeMae=" + nomeMae
				+ ", dataNascimento=" + dataNascimento + "]";
	}
}

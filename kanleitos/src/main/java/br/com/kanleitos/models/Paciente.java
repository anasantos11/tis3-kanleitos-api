package br.com.kanleitos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONException;
import org.json.JSONObject;


@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPaciente;
	
	@Column(name = "numProntuario", nullable = false)
	private int numProntuario;
	
	@Column(name = "nomePaciente", nullable = false)
	private String nomePaciente;
	@Column(name = "idade", nullable = false)
	private int idade;
	@Column(name = "genero", nullable = false)
	private String genero;
	@Column(name = "nomeMae", nullable = false)
	private String nomeMae;
	@Column(name = "dataNascimento", nullable = false)
	private String dataNascimento;
	
	private static class PacienteKeys {
		private static final String NUM_PRONTUARIO = "numProntuario";
		private static final String NOME_PACIENTE = "nomePaciente";
		private static final String IDADE = "idade";
		private static final String GENERO = "genero";
		private static final String NOME_MAE = "nomeMae";
		private static final String DATA_NASCIMENTO = "dataNascimento";
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

	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}   
	public int getNumProntuario() {
		return this.numProntuario;
	}

	public void setNumProntuario(int numProntuario) {
		this.numProntuario = numProntuario;
	}   
	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}   
	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
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

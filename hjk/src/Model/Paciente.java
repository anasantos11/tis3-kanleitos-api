	package Bases;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Calendar;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pacientes
 *
 */
@Entity
@Table(name = "PACIENTE")

public class Paciente implements Serializable {

	@Id
	@GeneratedValue
	private Integer idPaciente;
	private Integer numProntuario;
	private String nomePaciente;
	private Integer idade;
	private String genero;
	private String nomeMae;
	private Calendar dataNascimento;
	private static final long serialVersionUID = 1L;
 
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
	public Calendar getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", numProntuario=" + numProntuario + ", nomePaciente="
				+ nomePaciente + ", idade=" + idade + ", genero=" + genero + ", nomeMae=" + nomeMae
				+ ", dataNascimento=" + dataNascimento + "]";
	}
}

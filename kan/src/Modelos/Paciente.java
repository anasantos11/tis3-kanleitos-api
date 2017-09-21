package Modelos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Paciente {
	private int idPaciente;
	private int numProntuario;
	private String nomePaciente;
	private Calendar dataNascimento;
	private int idade;
	private String genero;
	private String nomeMae;

	public Paciente(int numProntuario, String nomePaciente, Calendar dataNascimento, int idade, String genero, String nomeMae) {
		setNumProntuario(numProntuario);
		setNomePaciente(nomePaciente);
		setIdade(idade);
		setGenero(genero);
		setNomeMae(nomeMae);
		setDataNascimento(dataNascimento);
	}
	
	public Paciente() {
		setNumProntuario(-1);
		setNomePaciente(null);
		setIdade(-1);
		setGenero(null);
		setNomeMae(null);
		setDataNascimento(null);
	}

	public int getNumProntuario() {
		return numProntuario;
	}

	public void setNumProntuario(int numProntuario) {
		this.numProntuario = numProntuario;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		String data;
		if (dataNascimento != null) {
			data = new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento.getTime());
		}else {
			data = null;
		}
		return "Paciente [idPaciente=" + idPaciente + ", numProntuario=" + numProntuario + ", nomePaciente="
				+ nomePaciente + ", dataNascimento=" + data + ", idade=" + idade + ", genero=" + genero
				+ ", nomeMae=" + nomeMae + "]";
	}
	

}

package Bases;

public class Paciente {
	private int idPaciente;
	private int numProntuario;
	private String nomePaciente;
	private int idade;
	private String genero;

	public Paciente(int numProntuario, String nomePaciente, int idade, String genero) {
		setNumProntuario(numProntuario);
		setNomePaciente(nomePaciente);
		setIdade(idade);
		setGenero(genero);
	}
	
	public Paciente() {
		setNumProntuario(-1);
		setNomePaciente(null);
		setIdade(-1);
		setGenero(null);
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
	
	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", numProntuario=" + numProntuario + ", nomePaciente="
				+ nomePaciente + ", idade=" + idade + ", genero=" + genero + "]";
	}

}
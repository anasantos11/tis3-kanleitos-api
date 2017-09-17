package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = {"nome", "sobreNome","idade", "cpf"})

public class Pessoa {
	private String nome;
	private String sobreNome;
	private String cpf; 
	private int idade;
	
	public Pessoa(String nome, String sobrenome, String cpf, int idade) {
		setNome(nome);
		setSobrenome(sobrenome);
		setCpf(cpf);
		setIdade(idade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobreNome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobreNome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "Pessoas [nome=" + nome + ", sobrenome=" + sobreNome + ", cpf=" + cpf + ", idade=" + idade + "]";
	}
}

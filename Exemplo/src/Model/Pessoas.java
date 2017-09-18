package Model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListaPessoas")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Pessoas {
	
	@XmlElement(name = "pessoas")
	List<Pessoa> lista_pessoas;

	public Pessoas(List<Pessoa> lista_pessoas) {
		setLista_pessoas(lista_pessoas);
	}
	
	public Pessoas() {
		setLista_pessoas(null);
	}

	public List<Pessoa> getLista_pessoas() {
		return lista_pessoas;
	}

	public void setLista_pessoas(List<Pessoa> lista_pessoas) {
		this.lista_pessoas = lista_pessoas;
	}
}

package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListaPacientes")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Pacientes {
	
	@XmlElement(name = "pacientes")
	List<Paciente> pacientes = new ArrayList<Paciente>();	

	public Pacientes(List<Paciente> pacientes) {
		super();
		this.pacientes = pacientes;
	}
	
	public Pacientes() {
		this.pacientes = null;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	
}

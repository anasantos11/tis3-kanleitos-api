package br.com.kanleitos.controllers;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.repository.PacienteRepository;
import br.com.kanleitos.util.Resposta;

@Controller

public class PacienteController{

	@Autowired
	private PacienteRepository repository;

	@RequestMapping(value = "CadastroPaciente", method = org.springframework.web.bind.annotation.RequestMethod.POST,consumes="application/json")
	public @ResponseBody String cadastrarPaciente(@RequestBody String json) throws JSONException {

		Paciente p = new Paciente(new JSONObject(json));
		boolean exists = repository.existsByNumProntuario(p.getNumProntuario());

		if(!exists) {
			repository.save(p);
			return Resposta.respostaToJson(false, p);
		}else {
			return Resposta.respostaToJson(true, p);
		}
	}
	
	@RequestMapping(value = "ListaPacientes", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarPacientes() throws JSONException {
		Iterable<Paciente> pacientes = repository.findAll();
		return Resposta.respostaToGson(pacientes);
	}
	
	@RequestMapping(value = "Paciente", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String getPaciente(@RequestParam  String numProntuario, String nomeMae) throws JSONException {
		Iterable<Paciente> paciente;
		if(numProntuario != null && !numProntuario.isEmpty()) {
			paciente = repository.findByNumProntuario(Long.parseLong(numProntuario));
		}else {
			paciente = repository.findByNomeMae(nomeMae);
		}

		return Resposta.respostaToGson(paciente);
	}
	
	

}

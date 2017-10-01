package br.com.kanleitos.controllers;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.repository.PacienteRepository;

@Controller
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@RequestMapping(value = "Cadastro/paciente", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String cadastrarPaciente(@RequestBody String json) throws JSONException {
		Paciente p = new Paciente(new JSONObject(json));
		repository.save(p);

		return "OK";
	}

}

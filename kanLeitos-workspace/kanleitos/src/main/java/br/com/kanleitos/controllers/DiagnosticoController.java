package br.com.kanleitos.controllers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kanleitos.models.Diagnostico;
import br.com.kanleitos.repository.DiagnosticoRepository;

@Controller
public class DiagnosticoController{

	@Autowired
	private DiagnosticoRepository repository;

	@RequestMapping(value = "Diagnosticos", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarDiagnosticos() throws JSONException {
		Iterable<Diagnostico> diagnosticos = repository.findAll();
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(diagnosticos);
		return d;
	}

}

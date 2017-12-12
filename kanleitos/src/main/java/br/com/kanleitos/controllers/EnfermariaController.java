package br.com.kanleitos.controllers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.repository.EnfermariaRepository;

@Controller
public class EnfermariaController{

	@Autowired
	private EnfermariaRepository repository;

	@RequestMapping(value = "Enfermarias", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarEnfermarias() throws JSONException {
		Iterable<Enfermaria> enfermarias = repository.findAll();
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(enfermarias);
		return d;
	}

}

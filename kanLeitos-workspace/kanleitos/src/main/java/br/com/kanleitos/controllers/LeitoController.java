package br.com.kanleitos.controllers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kanleitos.models.Leito;
import br.com.kanleitos.repository.LeitoRepository;

@Controller
public class LeitoController{

	@Autowired
	private LeitoRepository repository;

	@RequestMapping(value = "Leitos", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarLeitos() throws JSONException {
		Iterable<Leito> leitos = repository.findAll();
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(leitos);
		return d;
	}

}

package br.com.kanleitos.controllers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Ala;
import br.com.kanleitos.repository.AlaRepository;
import br.com.kanleitos.util.Resposta;

@Controller
public class AlaController{

	@Autowired
	private AlaRepository repository;

	@RequestMapping(value = "Alas", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarAlas() throws JSONException {
		Iterable<Ala> alas = repository.findAll();
		return Resposta.respostaToGson(alas);
	}

}

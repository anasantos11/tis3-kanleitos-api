package br.com.kanleitos.controllers;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.repository.LeitoRepository;

@Controller
public class LeitoController{

	@Autowired
	private LeitoRepository repository;

	@Autowired
	private EnfermariaRepository repositoryEnfermaria;
	
	@RequestMapping(value = "Leitos", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarLeitos() throws JSONException {
		Iterable<Leito> leitos = repository.findAll();
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(leitos);
		return d;
	}
	
	@RequestMapping(value = "GetLeitosEnfermaria", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String getPaciente(@RequestParam String idEnfermaria) throws JSONException {
		List<Leito> leitos = null;
		Enfermaria enfermaria = null;
		if(idEnfermaria != null && !idEnfermaria.isEmpty()) {
			enfermaria = repositoryEnfermaria.findOne(Integer.parseInt(idEnfermaria));
			
			if(enfermaria != null) {
				leitos = repository.findByEnfermaria(enfermaria);
			}
		}
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(leitos);
		return d;
	}

}

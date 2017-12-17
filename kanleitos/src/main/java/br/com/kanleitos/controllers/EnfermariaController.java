package br.com.kanleitos.controllers;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Ala;
import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.repository.AlaRepository;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.util.Resposta;

@Controller
public class EnfermariaController{

	@Autowired
	private EnfermariaRepository repository;

	@Autowired
	private AlaRepository alaRepository;
	
	@RequestMapping(value = "Enfermarias", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarEnfermarias() throws JSONException {
		Iterable<Enfermaria> enfermarias = repository.findAll();
		return Resposta.respostaToGson(enfermarias);
	}
	
	@RequestMapping(value = "GetEnfermariasByAlas", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarEnfermariasByAlas(@RequestParam String idAla) throws JSONException {
		List<Enfermaria> enfermarias = null;
		Ala ala = null;
		if(idAla != null && !idAla.isEmpty()) {
			ala = alaRepository.findOne(Long.parseLong(idAla));
			if(ala != null) {
				enfermarias = repository.findByAla(ala);
			}
		}
		
		return Resposta.respostaToGson(enfermarias);
	}

}

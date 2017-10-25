package br.com.kanleitos.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.repository.RegistroInternacaoRepository;

@Controller
public class KanController {

	@Autowired
	private RegistroInternacaoRepository registroRepository;

	@RequestMapping(value = "KanbanInternacoes", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String getKanbanInternacoes(@RequestParam  String classificacao) throws JSONException {
		Iterable<RegistroInternacao> registros = registroRepository.findByClassificacao(classificacao);
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(registros);
		return d;
	}

}

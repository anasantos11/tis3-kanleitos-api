package br.com.kanleitos.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.PedidoInternacaoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.TipoStatusLeito;

@Controller
public class RegistroInternacaoController {
	
	@Autowired
	private RegistroInternacaoRepository registroRepository;
	
	@Autowired
	private PedidoInternacaoRepository pedidoRepository;

	@Autowired
	private LeitoRepository leitoRepository;

	@Autowired
	private EnfermariaRepository enfermariaRepository;

	@RequestMapping(value = "RegistroInternacao", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String registroInternacao(@RequestBody String json) throws JSONException {
		String decoded = null;
		try {
			decoded = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject(decoded);
		RegistroInternacao r = new RegistroInternacao(jsonObject);
		//Get PedidoInternacao Enfermaria e Leito
		r.setPedidoInternacao(pedidoRepository.findOne(jsonObject.getInt("idPedido")));
		r.setEnfermaria(enfermariaRepository.findOne(jsonObject.getInt("idEnfermaria")));
		r.setLeito(leitoRepository.findOne(jsonObject.getInt("idLeito")));	
		r.setDiasInternacao(0);
		r.setClassificacao("Verde");
		registroRepository.save(r);
		
		//Alterar Status do Leito para Ocupado
		r.getLeito().setStatusLeito(TipoStatusLeito.OCUPADO_COMUM);	
		leitoRepository.save(r.getLeito());
		
		Gson gson = new GsonBuilder().create();
		String response = gson.toJson(r);
		return response;
	}
	
	@RequestMapping(value = "PacientesInternados", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarInternacoes() throws JSONException {
		Iterable<RegistroInternacao> internacoes = registroRepository.findAll();
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(internacoes);
		return d;
	}
}

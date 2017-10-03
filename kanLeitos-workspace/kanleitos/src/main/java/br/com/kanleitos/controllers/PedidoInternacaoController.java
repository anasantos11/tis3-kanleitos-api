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

import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.repository.PedidoInternacaoRepository;
import br.com.kanleitos.service.models.CadastroPacienteResposta;

@Controller
public class PedidoInternacaoController{

	@Autowired
	private PedidoInternacaoRepository repository;

	@RequestMapping(value = "PedidoInternacao", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String pedidoInternacao(@RequestBody String json) throws JSONException {
		boolean erroFlag = true;
		String decoded = null;
		try {
			decoded = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		PedidoInternacao p = new PedidoInternacao(new JSONObject(decoded));
		repository.save(p);
		erroFlag = false;

		CadastroPacienteResposta responseObject = new CadastroPacienteResposta(erroFlag);
		responseObject.setIdPaciente(Integer.toString(p.getIdPedidoInternacao()));
		String response = responseObject.toJson().toString();
		return response;
	}

}

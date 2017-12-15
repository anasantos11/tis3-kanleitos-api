package br.com.kanleitos.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.repository.AlaRepository;
import br.com.kanleitos.repository.DiagnosticoRepository;
import br.com.kanleitos.repository.PacienteRepository;
import br.com.kanleitos.repository.PedidoInternacaoRepository;

@Controller
public class PedidoInternacaoController {

	@Autowired
	private PedidoInternacaoRepository repository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private AlaRepository repositoryAla;

	@Autowired
	private DiagnosticoRepository repositoryDiagnostico;

	@RequestMapping(value = "PedidoInternacao", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String pedidoInternacao(@RequestBody String json) throws JSONException {
		String decoded = null;
		try {
			decoded = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject(decoded);
		PedidoInternacao p = new PedidoInternacao(jsonObject);
		//Get Paciente Ala e Diagnotisco
		p.setPaciente(pacienteRepository.findByNumProntuario(jsonObject.getInt("numProntuario")).get(0));
		p.setAla(repositoryAla.findOne((jsonObject.getInt("idAla"))));
		p.setDiagnostico(repositoryDiagnostico.findOne((jsonObject.getInt("idDiagnostico"))));
		
		
		
		repository.save(p);
		Gson gson = new GsonBuilder().create();
		String response = gson.toJson(p);
		return response;
	}
	
	@RequestMapping(value = "GetPedidoInternacao", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String getPaciente(@RequestParam  String numProntuario, String idPedidoInternacao) throws JSONException {
		List<PedidoInternacao> pedido = null;
		List<Paciente> paciente = null;
		if(numProntuario != null && !numProntuario.isEmpty()) {
			paciente = pacienteRepository.findByNumProntuario(Integer.parseInt(numProntuario));
			if(paciente.size() > 0) {
				long pront = paciente.get(0).getNumProntuario();
				if( pront > 0)
					pedido = repository.findByPaciente(paciente.get(0));
			}
		}else {
			//paciente = repository.findOne(Integer.parseInt(idPedidoInternacao));
		}

		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(pedido.get(0));
		return d;
	}
	
	@RequestMapping(value = "ListaPedidos", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarPedidos() throws JSONException {
		Iterable<PedidoInternacao> pedidos = repository.findAll();
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(pedidos);
		return d;
	}

}

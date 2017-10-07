package br.com.kanleitos.controllers;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import br.com.kanleitos.repository.PacienteRepository;
import br.com.kanleitos.service.models.CadastroPacienteResposta;

@Controller
public class PacienteController{

	@Autowired
	private PacienteRepository repository;

	@RequestMapping(value = "CadastroPaciente", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String cadastrarPaciente(@RequestBody String json) throws JSONException {
		boolean erroFlag = true;
		String decoded = null;
		try {
			decoded = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Paciente p = new Paciente(new JSONObject(decoded));
		repository.save(p);
		erroFlag = false;

		//CadastroPacienteResposta responseObject = new CadastroPacienteResposta(erroFlag);
		//responseObject.setIdPaciente(Integer.toString(p.getIdPaciente()));
		//String response = responseObject.toJson().toString();
		Gson gson = new GsonBuilder().create();
		String response = gson.toJson(p);
		return response;
	}
	
	@RequestMapping(value = "ListaPacientes", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarDiagnosticos() throws JSONException {
		Iterable<Paciente> pacientes = repository.findAll();
		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(pacientes);
		return d;
	}
	
	@RequestMapping(value = "Paciente", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String getPaciente(@RequestParam  String numProntuario, String nomeMae) throws JSONException {
		Iterable<Paciente> paciente;
		if(numProntuario != null && !numProntuario.isEmpty()) {
			paciente = repository.findByNumProntuario(Integer.parseInt(numProntuario));
		}else {
			paciente = repository.findByNomeMae(nomeMae);
		}

		Gson gson = new GsonBuilder().create();
		String d = gson.toJson(paciente);
		return d;
	}
	
	

}

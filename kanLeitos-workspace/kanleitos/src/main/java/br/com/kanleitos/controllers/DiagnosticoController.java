package br.com.kanleitos.controllers;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Diagnostico;
import br.com.kanleitos.repository.DiagnosticoRepository;
import br.com.kanleitos.service.models.DiagnosticoResposta;

@Controller
public class DiagnosticoController{

	@Autowired
	private DiagnosticoRepository repository;

	@RequestMapping(value = "Diagnosticos", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarDiagnosticos() throws JSONException {
		boolean erroFlag = true;

		Iterable<Diagnostico> diagnosticos = repository.findAll();
		
		erroFlag = false;
		DiagnosticoResposta responseObject = new DiagnosticoResposta(erroFlag);
		diagnosticos.forEach(x ->{
			responseObject.setIdDiagnostico(Integer.toString(x.getIdDiagnostico()));
			responseObject.setCID(x.getCID());
			responseObject.setDescricaoDiagnostico(x.getDescricaoDiagnostico());
			responseObject.setTempoPermanencia(Integer.toString(x.getTempoPermanencia()));
		});

		String response = responseObject.toJson().toString();
		return response;
	}

}

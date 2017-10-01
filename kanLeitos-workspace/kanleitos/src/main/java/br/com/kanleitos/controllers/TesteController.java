package br.com.kanleitos.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.repository.PacienteRepository;

@SuppressWarnings("serial")
@WebServlet("/pacientes")
public class TesteController extends HttpServlet {
	private static JSONObject jObj = null;

	@Autowired
	private PacienteRepository repository;

	@RequestMapping(value = "Cadastro/paciente", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String cadastrarPaciente(@RequestBody String json) throws JSONException {
		Paciente p = new Paciente(new JSONObject(json));
		repository.save(p);

		return "OK";
	}

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletRequest response)
			throws javax.servlet.ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}

		try {
			jObj = new JSONObject(sb.toString());
			Paciente p = new Paciente();

			p.setDataNascimento(jObj.getString("DataNascimento"));
			p.setGenero(jObj.getString("Genero"));
			p.setIdade(Integer.parseInt(jObj.getString("Idade")));
			p.setNomeMae(jObj.getString("NomeMae"));
			p.setNomePaciente(jObj.getString("NomePaciente"));
			p.setNumProntuario(Integer.parseInt(jObj.getString("NumProntuario")));
			
			repository.save(p);

		} catch (JSONException e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}

	}

}

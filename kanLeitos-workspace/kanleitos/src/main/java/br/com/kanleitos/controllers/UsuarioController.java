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

import br.com.kanleitos.models.Usuario;
import br.com.kanleitos.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "Login", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String json) throws JSONException {
		String decoded = null;
		try {
			decoded = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject(decoded);
		Usuario user = new Usuario(jsonObject);
		// Encode senha
		user.encodeSenha();

		Usuario userDataBase = usuarioRepository.findByLogin(user.getLogin());
		boolean usuarioValidado = userDataBase != null ? userDataBase.getSenha().equals(user.getSenha()) : false;

		// Montando resposta
		JSONObject resposta = new JSONObject();
		resposta.put("usuarioValidado", usuarioValidado);

		return resposta.toString();
	}

	@RequestMapping(value = "CriarUsuario", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public @ResponseBody String criarUsuario(@RequestBody String json) throws JSONException {
		String decoded = null;
		try {
			decoded = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject(decoded);
		Usuario user = new Usuario(jsonObject);
		// Encode senha
		user.encodeSenha();

		usuarioRepository.save(user);
		// Montando resposta
		
		JSONObject resposta = new JSONObject();
		resposta.put("codigo", 0);

		return resposta.toString();
	}

}

package br.com.kanleitos.controllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.repository.EnfermariaRepository;
import br.com.kanleitos.repository.LeitoRepository;
import br.com.kanleitos.repository.PedidoInternacaoRepository;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.Classificacao;
import br.com.kanleitos.util.Resposta;
import br.com.kanleitos.util.StatusPedido;
import br.com.kanleitos.util.StatusRegistro;
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

		JSONObject jsonObject = new JSONObject(json);
		RegistroInternacao r = new RegistroInternacao(jsonObject);
		// Get PedidoInternacao Enfermaria e Leito
		r.setPedidoInternacao(pedidoRepository.findOne(jsonObject.getLong("idPedido")));
		r.setEnfermaria(enfermariaRepository.findOne(jsonObject.getLong("idEnfermaria")));
		r.setLeito(leitoRepository.findOne(jsonObject.getLong("idLeito")));
		r.setClassificacao(Classificacao.VERDE);
		r.setStatusRegistro(StatusRegistro.EM_ANDAMENTO);

		List<RegistroInternacao> registros = registroRepository
				.findByPedidoInternacaoAndStatusRegistro(r.getPedidoInternacao(), StatusRegistro.EM_ANDAMENTO);
		
		if (registros.size() == 0) {
			registroRepository.save(r);
			// Alterar Status do Leito para Ocupado
			r.getLeito().setStatusLeito(TipoStatusLeito.OCUPADO_COMUM);
			leitoRepository.save(r.getLeito());

			// Atualizar Pedido para concluido
			r.getPedidoInternacao().setStatusPedido(StatusPedido.CONCLUIDO);
			pedidoRepository.save(r.getPedidoInternacao());

			return Resposta.respostaToJson(false, r);
		} else {
			return Resposta.respostaToJson(true, r);
		}

	}

	@RequestMapping(value = "PacientesInternados", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String listarInternacoes() throws JSONException {
		Iterable<RegistroInternacao> internacoes = registroRepository.findAll();
		return Resposta.respostaToGson(internacoes);
	}
}

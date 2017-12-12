package br.com.kanleitos.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.repository.RegistroInternacaoRepository;
import br.com.kanleitos.util.Classificacao;
import br.com.kanleitos.util.StatusRegistro;

@Controller
public class AtualizarRegistrosController {
	@Autowired
	private RegistroInternacaoRepository registroRepository;

	@RequestMapping(value = "AtualizarInternacoes", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody int atualizarInternacoes() throws JSONException {
		List<RegistroInternacao> registros = registroRepository.findByStatusRegistro(StatusRegistro.EM_ANDAMENTO);
		registros.forEach(x -> {
			x.setDiasInternacao(calcularDiasInternados(x.getDataInternacao()));
			x.setClassificacao(calcularClassificacao(x));
		});
		registroRepository.save(registros);
		return 0;
	}

	public static int calcularDiasInternados(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dataAdmissaoTime = LocalDateTime.parse(data, formatter);
		Duration duracao = Duration.between(dataAdmissaoTime, LocalDateTime.now());
		return (int) duracao.toDays();
	}

	public static Classificacao calcularClassificacao(RegistroInternacao registro) {
		int tempoPermanenciaDiagnostico = registro.getPedidoInternacao().getDiagnostico().getTempoPermanencia();
		float percentual = (float) (registro.getDiasInternacao() * 100) / tempoPermanenciaDiagnostico;

		if (percentual <= 50) {
			return Classificacao.VERDE;
		} else if (percentual <= 100) {
			return Classificacao.AMARELO;
		} else {
			return Classificacao.VERMELHO;
		}
	}
}

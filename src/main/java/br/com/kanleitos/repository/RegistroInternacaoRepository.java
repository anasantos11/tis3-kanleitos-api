package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.models.RegistroInternacao;
import br.com.kanleitos.util.Classificacao;
import br.com.kanleitos.util.StatusRegistro;

@Repository
public interface RegistroInternacaoRepository extends CrudRepository<RegistroInternacao, Long> {
	List<RegistroInternacao> findByClassificacao(Classificacao classificacao);
	List<RegistroInternacao> findByStatusRegistro(StatusRegistro statusRegistro);
	List<RegistroInternacao> findByPedidoInternacaoAndStatusRegistro(PedidoInternacao pedidoInternacao, StatusRegistro statusRegistro);

}

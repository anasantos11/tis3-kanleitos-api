package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.models.PedidoInternacao;

@Repository
public interface PedidoInternacaoRepository extends CrudRepository<PedidoInternacao, Integer> {
	List<PedidoInternacao> findByPaciente(Paciente paciente);
}

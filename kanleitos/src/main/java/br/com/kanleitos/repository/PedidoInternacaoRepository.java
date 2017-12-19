package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Paciente;
import br.com.kanleitos.models.PedidoInternacao;
import br.com.kanleitos.util.StatusPedido;

@Repository
public interface PedidoInternacaoRepository extends CrudRepository<PedidoInternacao, Long> {
	List<PedidoInternacao> findByPaciente(Paciente paciente);

	List<PedidoInternacao> findByStatusPedidoOrStatusPedido(StatusPedido pendente, StatusPedido atrasado);

	List<PedidoInternacao> findByPacienteAndStatusPedido(Paciente paciente, StatusPedido pendente);
}

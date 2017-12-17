package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Integer> {
	List<Paciente> findByNumProntuario(long numProntuario);
	boolean existsByNumProntuario(long numProntuario);
	List<Paciente> findByNomeMae(String nomeMae);
	
}

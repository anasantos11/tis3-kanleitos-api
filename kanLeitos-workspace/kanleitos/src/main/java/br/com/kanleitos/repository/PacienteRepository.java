package br.com.kanleitos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Integer> {

}

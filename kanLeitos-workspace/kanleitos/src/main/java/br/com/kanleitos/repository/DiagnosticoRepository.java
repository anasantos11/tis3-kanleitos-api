package br.com.kanleitos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.service.models.Diagnostico;

@Repository
public interface DiagnosticoRepository extends CrudRepository<Diagnostico, Integer> {

}

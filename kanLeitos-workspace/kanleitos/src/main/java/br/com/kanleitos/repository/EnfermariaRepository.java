package br.com.kanleitos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Enfermaria;

@Repository
public interface EnfermariaRepository extends CrudRepository<Enfermaria, Integer> {

}

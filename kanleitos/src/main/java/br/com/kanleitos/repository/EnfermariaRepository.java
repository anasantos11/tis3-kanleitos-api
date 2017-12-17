package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Ala;
import br.com.kanleitos.models.Enfermaria;

@Repository
public interface EnfermariaRepository extends CrudRepository<Enfermaria, Long> {
	List<Enfermaria> findByAla(Ala ala);
}

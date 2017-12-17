package br.com.kanleitos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Ala;

@Repository
public interface AlaRepository extends CrudRepository<Ala, Long> {

}

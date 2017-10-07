package br.com.kanleitos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Leito;

@Repository
public interface LeitoRepository extends CrudRepository<Leito, Integer> {

}

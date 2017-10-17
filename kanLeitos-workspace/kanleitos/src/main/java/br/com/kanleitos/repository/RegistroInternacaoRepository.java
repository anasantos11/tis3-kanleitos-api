package br.com.kanleitos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.RegistroInternacao;

@Repository
public interface RegistroInternacaoRepository extends CrudRepository<RegistroInternacao, Integer> {

}

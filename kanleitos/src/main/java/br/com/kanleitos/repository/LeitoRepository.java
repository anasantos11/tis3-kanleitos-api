package br.com.kanleitos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Enfermaria;
import br.com.kanleitos.models.Leito;
import br.com.kanleitos.util.TipoStatusLeito;

@Repository
public interface LeitoRepository extends CrudRepository<Leito, Long> {
	List<Leito> findByEnfermariaAndStatusLeito(Enfermaria enfermaria, TipoStatusLeito desocupado);
}

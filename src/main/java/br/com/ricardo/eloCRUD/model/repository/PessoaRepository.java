package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeStartingWith(String nome);
}

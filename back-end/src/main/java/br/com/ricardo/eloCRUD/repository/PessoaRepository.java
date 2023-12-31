package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContaining(String nome);
}

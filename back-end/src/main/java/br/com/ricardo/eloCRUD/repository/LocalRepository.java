package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    List<Local> findByDescricaoContaining(String descricao);
}

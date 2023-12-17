package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {

    List<Status> findByDescricaoContaining(String descricao);
}

package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    @Query(value = "select l from Local l where l.descricao ilike %:descricao%")
    Page<Local> getLocalByDescricao(String descricao, Pageable pageable);
}

package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query(value = "select s from Status s where s.descricao ilike %:descricao%")
    Page<Status> getStatusByDescricao(String descricao, Pageable pageable);
}

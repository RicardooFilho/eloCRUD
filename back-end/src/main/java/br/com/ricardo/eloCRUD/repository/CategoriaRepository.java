package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "select c from Categoria c where c.descricao ilike %:descricao%")
    Page<Categoria> getCategoriaByDescricao(String descricao, Pageable pageable);
}

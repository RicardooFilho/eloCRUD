package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByDescricaoContaining(String descricao);
}

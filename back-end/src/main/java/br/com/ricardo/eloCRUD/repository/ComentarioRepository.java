package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByDescricaoContaining(String descricao);
}

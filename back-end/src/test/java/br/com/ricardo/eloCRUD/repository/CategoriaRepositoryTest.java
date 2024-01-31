package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/sql/categoria.sql")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void buscaCategoriaPorDescricaoContainingTest() {
        List<Categoria> categorias = categoriaRepository.findByDescricaoContaining("a");

        assertThat(categorias).extracting(Categoria::getId)
                .hasSize(2)
                .containsExactlyInAnyOrder(12L, 11L);
    }

    @Test
    public void salvaCategoriaTest() {
        Categoria categoria = new Categoria();

        categoria.setDescricao("Solicitação");

        Categoria novaCategoria = categoriaRepository.save(categoria);

        assertThat(novaCategoria).isNotNull();
        assertThat(novaCategoria.getId()).isNotNull().isEqualTo(1L);
        assertThat(novaCategoria.getDescricao()).isEqualTo("Solicitação");
    }

    @Test
    public void atualizacaCategoriaTest() {
        Categoria categoria = categoriaRepository.findById(10L).get();
        categoria.setDescricao("Vistoria");

        Categoria categoriaAtualizada = categoriaRepository.save(categoria);

        assertThat(categoriaAtualizada.getId()).isNotNull().isEqualTo(10L);
        assertThat(categoriaAtualizada.getDescricao()).isEqualTo("Vistoria");
    }

    @Test
    public void deletaCategoriaTest() {
        categoriaRepository.deleteById(10L);

        List<Categoria> categorias = categoriaRepository.findAll();

        assertThat(categorias).extracting(Categoria::getId)
                .hasSize(2)
                .containsExactlyInAnyOrder(11L, 12L);
    }

}*/
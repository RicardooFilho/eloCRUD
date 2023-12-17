package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Comentario;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.domain.Tarefa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/categoria.sql", "/sql/local.sql", "/sql/pessoa.sql", "/sql/status.sql", "/sql/tarefa.sql", "/sql/comentario.sql"})

class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Test
    public void buscaComentarioPorDescricaoContainingTest() {
        List<Comentario> comentarios = comentarioRepository.findByDescricaoContaining("amo");

        assertThat(comentarios).extracting(Comentario::getId)
                .hasSize(1)
                .containsExactlyInAnyOrder(12L);
    }

    @Test
    public void salvaComentarioTest() {
        Comentario comentario = new Comentario();
        Pessoa pessoa = new Pessoa();
        Tarefa tarefa = new Tarefa();

        pessoa.setId(10L);
        tarefa.setNumero(10L);

        comentario.setDescricao("Boa tarefa!");
        comentario.setPessoaId(pessoa);
        comentario.setDataComentario(LocalDate.now());
        comentario.setTarefaNumero(tarefa);

        Comentario novoComentario = comentarioRepository.save(comentario);

        assertThat(novoComentario).isNotNull();
        assertThat(novoComentario.getId()).isNotNull().isEqualTo(1L);
        assertThat(novoComentario.getDescricao()).isEqualTo("Boa tarefa!");
        assertThat(novoComentario.getPessoaId()).isEqualTo(pessoa);
        assertThat(novoComentario.getDataComentario()).isEqualTo(LocalDate.now());
        assertThat(novoComentario.getTarefaNumero()).isEqualTo(tarefa);
    }

    @Test
    public void atualizaComentarioTest() {
        Pessoa pessoa = new Pessoa();
        Tarefa tarefa = new Tarefa();

        pessoa.setId(10L);
        tarefa.setNumero(10L);

        Comentario comentario = comentarioRepository.findById(10L).get();

        comentario.setDescricao("Ótima tarefa!");

        Comentario comentarioAtualizado = comentarioRepository.save(comentario);

        assertThat(comentarioAtualizado.getId()).isEqualTo(10L);
        assertThat(comentarioAtualizado.getDescricao()).isEqualTo("Ótima tarefa!");
        assertThat(comentarioAtualizado.getPessoaId()).isEqualTo(pessoa);
        assertThat(comentarioAtualizado.getDataComentario()).isEqualTo("2023-12-16");
        assertThat(comentarioAtualizado.getTarefaNumero()).isEqualTo(tarefa);
    }

    @Test
    public void deletaComentarioTest() {
        comentarioRepository.deleteById(10L);

        List<Comentario> comentarios = comentarioRepository.findAll();

        assertThat(comentarios).extracting(Comentario::getId)
                .hasSize(2)
                .containsExactlyInAnyOrder(12L, 11L);
    }

}
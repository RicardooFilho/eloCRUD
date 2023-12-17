package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.*;
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
@Sql(scripts = {"/sql/categoria.sql", "/sql/local.sql", "/sql/pessoa.sql", "/sql/status.sql", "/sql/tarefa.sql"})
public class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Test
    public void buscaTarefaPorDescricaoContainingTest() {
        List<Tarefa> tarefas = tarefaRepository.findByDescricaoContaining("Bug");

        assertThat(tarefas).extracting(Tarefa::getNumero)
                .hasSize(1)
                .containsExactlyInAnyOrder(10L);
    }

    @Test
    public void salvaTarefaTest() {
        Tarefa tarefa = new Tarefa();
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        Categoria categoria = new Categoria();
        Local local = new Local();
        Status status = new Status();

        pessoa1.setId(10L);
        pessoa2.setId(11L);
        categoria.setId(10L);
        local.setId(10L);
        status.setId(10L);

        tarefa.setExercicio(2023);
        tarefa.setRequerenteId(pessoa1);
        tarefa.setTitulo("Empenho sem número");
        tarefa.setCategoriaId(categoria);
        tarefa.setDescricao("Foi verificado que alguns empenhos estão sem número");
        tarefa.setRequeridoId(pessoa2);
        tarefa.setLocalDestinoId(local);
        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setStatusId(status);

        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        assertThat(tarefaSalva).isNotNull();
        assertThat(tarefaSalva.getNumero()).isEqualTo(1L);
        assertThat(tarefaSalva.getExercicio()).isEqualTo(2023);
        assertThat(tarefaSalva.getRequerenteId()).isEqualTo(pessoa1);
        assertThat(tarefaSalva.getTitulo()).isEqualTo("Empenho sem número");
        assertThat(tarefaSalva.getCategoriaId()).isEqualTo(categoria);
        assertThat(tarefaSalva.getDescricao()).isEqualTo("Foi verificado que alguns empenhos estão sem número");
        assertThat(tarefaSalva.getRequeridoId()).isEqualTo(pessoa2);
        assertThat(tarefaSalva.getLocalDestinoId()).isEqualTo(local);
        assertThat(tarefaSalva.getDataCriacao()).isEqualTo(LocalDate.now());
        assertThat(tarefaSalva.getStatusId()).isEqualTo(status);
    }

    @Test
    public void atualizaTarefaTest() {
        Pessoa pessoa1 = new Pessoa();
        Categoria categoria = new Categoria();
        Local local = new Local();
        Status status = new Status();

        pessoa1.setId(10L);
        categoria.setId(10L);
        local.setId(12L);
        status.setId(10L);

        Tarefa tarefa = tarefaRepository.findById(10L).get();

        tarefa.setDescricao("Módulo ARR");

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);

        assertThat(tarefaAtualizada).isNotNull();
        assertThat(tarefaAtualizada.getNumero()).isEqualTo(10L);
        assertThat(tarefaAtualizada.getExercicio()).isEqualTo(2023);
        assertThat(tarefaAtualizada.getRequerenteId()).isEqualTo(pessoa1);
        assertThat(tarefaAtualizada.getTitulo()).isEqualTo("Bug no OXY");
        assertThat(tarefaAtualizada.getCategoriaId()).isEqualTo(categoria);
        assertThat(tarefaAtualizada.getDescricao()).isEqualTo("Módulo ARR");
        assertThat(tarefaAtualizada.getRequeridoId()).isEqualTo(null);
        assertThat(tarefaAtualizada.getLocalDestinoId()).isEqualTo(local);
        assertThat(tarefaAtualizada.getDataCriacao()).isEqualTo("2023-12-16");
        assertThat(tarefaAtualizada.getStatusId()).isEqualTo(status);
    }

    @Test
    public void deletaTarefaTest() {
        tarefaRepository.deleteById(10L);

        List<Tarefa> tarefas = tarefaRepository.findAll();

        assertThat(tarefas).extracting(Tarefa::getNumero)
                .hasSize(2)
                .containsExactlyInAnyOrder(12L, 11L);
    }

}
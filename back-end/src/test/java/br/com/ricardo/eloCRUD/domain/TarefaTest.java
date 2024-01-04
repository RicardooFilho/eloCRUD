package br.com.ricardo.eloCRUD.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TarefaTest {

    @Test
    public void criacaoDeTarefaTest() {
        Tarefa tarefa = new Tarefa();

        tarefa.setNumero(1L);
        tarefa.setExercicio(2023);
        tarefa.setRequerente(new Pessoa());
        tarefa.setTitulo("BUG");
        tarefa.setCategoriaId(new Categoria());
        tarefa.setDescricao("Foi encontrado um BUG no OXY");
        tarefa.setRequeridoId(new Pessoa());
        tarefa.setLocalDestinoId(new Local());
        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setStatusId(new Status());

        assertThat(tarefa.getNumero()).isEqualTo(1L);
        assertThat(tarefa.getExercicio()).isEqualTo(2023);
        assertThat(tarefa.getRequerente()).isEqualTo(new Pessoa());
        assertThat(tarefa.getTitulo()).isEqualTo("BUG");
        assertThat(tarefa.getCategoriaId()).isEqualTo(new Categoria());
        assertThat(tarefa.getDescricao()).isEqualTo("Foi encontrado um BUG no OXY");
        assertThat(tarefa.getRequeridoId()).isEqualTo(new Pessoa());
        assertThat(tarefa.getLocalDestinoId()).isEqualTo(new Local());
        assertThat(tarefa.getDataCriacao()).isEqualTo(LocalDate.now());
        assertThat(tarefa.getStatusId()).isEqualTo(new Status());
    }

}
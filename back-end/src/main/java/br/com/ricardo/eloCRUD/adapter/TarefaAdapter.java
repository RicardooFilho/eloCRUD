package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.domain.Tarefa;
import br.com.ricardo.eloCRUD.dto.TarefaDTO;

import java.util.Objects;

public class TarefaAdapter implements Adapter<TarefaDTO, Tarefa> {

    @Override
    public Tarefa toEntity(TarefaDTO tarefaDTO) {
        return new Tarefa(tarefaDTO.getNumero(),
                        tarefaDTO.getExercicio(),
                        tarefaDTO.getRequerente(),
                        tarefaDTO.getTitulo(),
                        tarefaDTO.getCategoria(),
                        tarefaDTO.getDescricao(),
                        Objects.nonNull(tarefaDTO.getRequerido()) ? new Pessoa() : null,
                        tarefaDTO.getLocalDestino(),
                        tarefaDTO.getDataCriacao(),
                        tarefaDTO.getStatus());
    }

    @Override
    public TarefaDTO toDto(Tarefa tarefa) {
        return new TarefaDTO(tarefa.getNumero(),
                tarefa.getExercicio(),
                tarefa.getRequerente(),
                tarefa.getTitulo(),
                tarefa.getCategoria(),
                tarefa.getDescricao(),
                tarefa.getRequerido(),
                tarefa.getLocalDestino(),
                tarefa.getDataCriacao(),
                tarefa.getStatus());
    }
}

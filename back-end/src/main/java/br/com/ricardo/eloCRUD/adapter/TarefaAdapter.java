package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Tarefa;
import br.com.ricardo.eloCRUD.dto.TarefaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaAdapter implements Adapter<TarefaDTO, Tarefa> {

    @Autowired
    private PessoaAdapter pessoaAdapter;

    @Autowired
    private CategoriaAdapter categoriaAdapter;

    @Autowired
    private LocalAdapter localAdapter;

    @Autowired
    private StatusAdapter statusAdapter;

    @Override
    public Tarefa toEntity(TarefaDTO tarefaDTO) {
        return new Tarefa(tarefaDTO.getNumero(),
                        tarefaDTO.getExercicio(),
                        //Objects.nonNull(tarefaDTO.getRequerenteDto()) ? pessoaAdapter.toEntity(tarefaDTO.getRequerenteDto()) : null,
                        pessoaAdapter.toEntity(tarefaDTO.getRequerenteDto()),
                        tarefaDTO.getTitulo(),
                        categoriaAdapter.toEntity(tarefaDTO.getCategoriaDto()),
                        tarefaDTO.getDescricao(),
                        pessoaAdapter.toEntity(tarefaDTO.getRequeridoDto()),
                        localAdapter.toEntity(tarefaDTO.getLocalDestinoDto()),
                        tarefaDTO.getDataCriacao(),
                        statusAdapter.toEntity(tarefaDTO.getStatusDto()));
    }

    @Override
    public TarefaDTO toDto(Tarefa tarefa) {
        return new TarefaDTO(tarefa.getNumero(),
                tarefa.getExercicio(),
                //Objects.nonNull(tarefa.getRequerente()) ? pessoaAdapter.toDto(tarefa.getRequerente()) : null,
                pessoaAdapter.toDto(tarefa.getRequerente()),
                tarefa.getTitulo(),
                categoriaAdapter.toDto(tarefa.getCategoria()),
                tarefa.getDescricao(),
                pessoaAdapter.toDto(tarefa.getRequerido()),
                localAdapter.toDto(tarefa.getLocalDestino()),
                tarefa.getDataCriacao(),
                statusAdapter.toDto(tarefa.getStatus()));
    }
}
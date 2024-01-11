package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.adapter.TarefaAdapter;
import br.com.ricardo.eloCRUD.domain.Tarefa;
import br.com.ricardo.eloCRUD.dto.TarefaDTO;
import br.com.ricardo.eloCRUD.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaAdapter tarefaAdapter;

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public ResponseEntity<Page<TarefaDTO>> pegarTodasTarefas(Pageable pageable) {
        Page<TarefaDTO> tarefaDtoPage = tarefaRepository.findAll(pageable).map(tarefa -> tarefaAdapter.toDto(tarefa));

        return ResponseEntity.status(HttpStatus.OK).body(tarefaDtoPage);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<TarefaDTO> pegarTarefaPorId(@PathVariable Long numero) {
        TarefaDTO tarefaDto = tarefaAdapter.toDto(tarefaRepository.findById(numero).orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada")));

        return ResponseEntity.status(HttpStatus.OK).body(tarefaDto);
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody @Validated TarefaDTO novaTarefaDto) {
        Tarefa tarefaSalva = tarefaRepository.save(tarefaAdapter.toEntity(novaTarefaDto));

        TarefaDTO tarefaDto = tarefaAdapter.toDto(tarefaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaDto);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<TarefaDTO> editarTarefaPorId(@PathVariable Long numero, @RequestBody @Validated Tarefa novaTarefa) {
        Tarefa tarefaSalva = tarefaRepository.findById(numero).orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        tarefaSalva.setDescricao(novaTarefa.getDescricao());
        tarefaSalva.setCategoria(novaTarefa.getCategoria());
        tarefaSalva.setTitulo(novaTarefa.getTitulo());
        tarefaSalva.setLocalDestino(novaTarefa.getLocalDestino());
        tarefaSalva.setRequerente(novaTarefa.getRequerente());
        tarefaSalva.setRequerido(novaTarefa.getRequerido());
        tarefaSalva.setStatus(novaTarefa.getStatus());

        TarefaDTO tarefaDto = tarefaAdapter.toDto(tarefaRepository.save(tarefaSalva));

        return ResponseEntity.status(HttpStatus.OK).body(tarefaDto);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletarTarefaPorId(@PathVariable Long numero) {
        tarefaRepository.deleteById(numero);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

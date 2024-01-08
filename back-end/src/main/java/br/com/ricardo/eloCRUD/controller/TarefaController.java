package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Tarefa;
import br.com.ricardo.eloCRUD.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public ResponseEntity<List<Tarefa>> pegarTodasTarefas() {
        return ResponseEntity.ok(tarefaRepository.findAll());
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Tarefa> pegarTarefaPorId(@PathVariable Long numero) {
        return ResponseEntity.ok(tarefaRepository.findById(numero).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa novaTarefa) {
        Tarefa tarefa = tarefaRepository.save(novaTarefa);

        return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<Tarefa> editarTarefaPorId(@PathVariable Long numero, @RequestBody Tarefa novaTarefa) {
        Tarefa tarefaSalva = tarefaRepository.findById(numero).orElseThrow(null);

        tarefaSalva.setDescricao(novaTarefa.getDescricao());
        tarefaSalva.setCategoria(novaTarefa.getCategoria());
        tarefaSalva.setTitulo(novaTarefa.getTitulo());
        tarefaSalva.setLocalDestino(novaTarefa.getLocalDestino());
        tarefaSalva.setRequerente(novaTarefa.getRequerente());
        tarefaSalva.setRequerido(novaTarefa.getRequerido());
        tarefaSalva.setStatus(novaTarefa.getStatus());

        return ResponseEntity.ok(tarefaRepository.save(tarefaSalva));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletarTarefaPorId(@PathVariable Long numero) {
        tarefaRepository.deleteById(numero);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

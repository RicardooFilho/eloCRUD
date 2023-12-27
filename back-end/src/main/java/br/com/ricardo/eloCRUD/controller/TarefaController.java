package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Tarefa;
import br.com.ricardo.eloCRUD.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public ResponseEntity<List<Tarefa>> pegarTodasTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();

        if (tarefas.isEmpty()) {
            return new ResponseEntity<>(tarefas, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Tarefa> pegarTarefaPorId(@PathVariable Long numero) {
        Tarefa tarefa = tarefaRepository.findById(numero).orElse(null);

        return new ResponseEntity<>(tarefa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa novaTarefa) {
        Tarefa tarefa = tarefaRepository.save(novaTarefa);

        return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
    }

//    @PutMapping("/{numero}")
//    public ResponseEntity<Tarefa> editarTarefa(@PathVariable Long numero, @RequestBody Tarefa novaTarefa) {
//        Tarefa tarefaSalva = tarefaRepository.findById(numero).orElseThrow(null);
//
//      //  tarefaSalva.set
//    }
}

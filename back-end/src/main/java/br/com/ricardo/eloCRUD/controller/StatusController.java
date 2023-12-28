package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Status;
import br.com.ricardo.eloCRUD.repository.StatusRepository;
import org.hibernate.usertype.StaticUserTypeSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping
    public ResponseEntity<List<Status>> pegarTodosStatus() {
        return ResponseEntity.ok(statusRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> pegarStatusPorId(@PathVariable Long id) {
       return ResponseEntity.ok(statusRepository.findById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Status> criarStatus(@RequestBody Status novoStatus) {
        Status status = statusRepository.save(novoStatus);

        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> editarStatusPorId(@PathVariable Long id, @RequestBody Status novoStatus) {
        Status statusSalvo = statusRepository.findById(id).orElseThrow(null);

        statusSalvo.setDescricao(novoStatus.getDescricao());
        statusSalvo.setSituacao(novoStatus.getSituacao());

        return ResponseEntity.ok(statusRepository.save(statusSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStatusPorId (@PathVariable Long id) {
        statusRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

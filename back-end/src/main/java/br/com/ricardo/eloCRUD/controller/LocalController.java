package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locais")
public class LocalController {

    @Autowired
    private LocalRepository localRepository;

    @GetMapping
    public ResponseEntity<List<Local>> pegarTodosLocais() {
        return ResponseEntity.ok(localRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> pegarLocalPorId(@PathVariable Long id) {
        return ResponseEntity.ok(localRepository.findById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Local> criarLocal(@RequestBody Local novoLocal) {
        Local local = localRepository.save(novoLocal);

        return new ResponseEntity<>(local, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> editarLocalPorId(@PathVariable Long id, @RequestBody Local novoLocal) {
        Local localSalvo = localRepository.findById(id).orElseThrow(null);

        localSalvo.setDescricao(novoLocal.getDescricao());

        return ResponseEntity.ok(localRepository.save(localSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocalPorId(@PathVariable Long id) {
        localRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

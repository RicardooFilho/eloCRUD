package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/local")
public class LocalController {

    @Autowired
    private LocalRepository localRepository;

    @GetMapping
    public ResponseEntity<List<Local>> pegarTodosLocais() {
        List<Local> locais = localRepository.findAll();

        if (locais.isEmpty()) {
            return new ResponseEntity<>(locais, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(locais, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> pegarLocalPorId(@PathVariable Long id) {
        Local local = localRepository.findById(id).orElse(null);

        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Local> criarLocal(@RequestBody Local novoLocal) {
        Local local = localRepository.save(novoLocal);

        return new ResponseEntity<>(local, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> editarLocal(@PathVariable Long id, @RequestBody Local novoLocal) {
        Local localSalvo = localRepository.findById(id).orElseThrow(null);

        localSalvo.setDescricao(novoLocal.getDescricao());

        Local local = localRepository.save(localSalvo);

        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocal(@PathVariable Long id) {
        localRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

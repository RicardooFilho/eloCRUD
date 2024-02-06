package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import br.com.ricardo.eloCRUD.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locais")
public class LocalController {

    @Autowired
    private LocalService localService;

    @GetMapping
    public ResponseEntity<Page<LocalDTO>> getTodosLocaisDescricao(@RequestParam(value = "descricao", required = false) String descricao,
                                                                  Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.localService.getTodosLocaisDescricao(descricao, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<LocalDTO> getLocalPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.localService.getLocalPorId(id));
    }

    @GetMapping("/quantidade-locais")
    public ResponseEntity<Long> getQuantidadeLocais() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.localService.getQuantidadeLocais());
    }

    @PostMapping
    public ResponseEntity<Local> postLocal(@RequestBody @Validated Local novoLocal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.localService.saveLocal(novoLocal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> putLocalPorId(@PathVariable Long id, @RequestBody @Validated Local novoLocal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.localService.putLocal(id, novoLocal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocalPorId(@PathVariable Long id) {
        this.localService.deleteLocal(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}

package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Status;
import br.com.ricardo.eloCRUD.dto.StatusDTO;
import br.com.ricardo.eloCRUD.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<Page<StatusDTO>> getTodosStatusDescricao(@RequestParam(value = "descricao", required = false) String descricao,
                                                                   Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.statusService.getTodosStatusDescricao(descricao, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StatusDTO> getStatusPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.statusService.getStatusPorId(id));
    }

    @GetMapping("/quantidade-status")
    public ResponseEntity<Long> getQuantidadeStatus() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.statusService.getQuantidadeStatus());
    }

    @PostMapping
    public ResponseEntity<Status> postStatus(@RequestBody @Validated Status novoStatus) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.statusService.saveStatus(novoStatus));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Status> putStatusPorId(@PathVariable Long id, @RequestBody @Validated Status novoStatus) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.statusService.putStatus(id, novoStatus));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteStatusPorId (@PathVariable Long id) {
        this.statusService.deleteStatus(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}

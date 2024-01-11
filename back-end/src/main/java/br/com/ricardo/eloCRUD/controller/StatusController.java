package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.adapter.StatusAdapter;
import br.com.ricardo.eloCRUD.domain.Status;
import br.com.ricardo.eloCRUD.dto.StatusDTO;
import br.com.ricardo.eloCRUD.repository.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private StatusAdapter statusAdapter;

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping
    public ResponseEntity<Page<StatusDTO>> pegarTodosStatus(Pageable pageable) {
        Page<StatusDTO> statusDtoPage = statusRepository.findAll(pageable).map(status -> statusAdapter.toDto(status));

        return ResponseEntity.status(HttpStatus.OK).body(statusDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> pegarStatusPorId(@PathVariable Long id) {
        StatusDTO statusDto = statusAdapter.toDto(statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Status não encontrado")));

        return ResponseEntity.status(HttpStatus.OK).body(statusDto);
    }

    @PostMapping
    public ResponseEntity<StatusDTO> criarStatus(@RequestBody @Validated StatusDTO novoStatusDto) {
        Status statusSalvo = statusRepository.save(statusAdapter.toEntity(novoStatusDto));

        StatusDTO statusDto = statusAdapter.toDto(statusSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(statusDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusDTO> editarStatusPorId(@PathVariable Long id, @RequestBody @Validated Status novoStatus) {
        Status statusSalvo = statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Status não encontrado"));

        statusSalvo.setDescricao(novoStatus.getDescricao());
        statusSalvo.setSituacao(novoStatus.getSituacao());

        StatusDTO statusDto = statusAdapter.toDto(statusRepository.save(statusSalvo));

        return ResponseEntity.status(HttpStatus.OK).body(statusDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStatusPorId (@PathVariable Long id) {
        statusRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

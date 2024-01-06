package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.adapter.LocalAdapter;
import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import br.com.ricardo.eloCRUD.repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locais")
public class LocalController {

    @Autowired
    private LocalAdapter localAdapter;

    @Autowired
    private LocalRepository localRepository;

    @GetMapping
    public ResponseEntity<Page<LocalDTO>> pegarTodosLocais(Pageable pageable) {
        Page<LocalDTO> localDtoPage = localRepository.findAll(pageable).map(local -> localAdapter.toDto(local));

        return ResponseEntity.status(HttpStatus.OK).body(localDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> pegarLocalPorId(@PathVariable Long id) {
        LocalDTO localDTO = localAdapter.toDto(localRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Local não encontrado")));

        return ResponseEntity.status(HttpStatus.OK).body(localDTO);
    }

    @PostMapping
    public ResponseEntity<LocalDTO> criarLocal(@RequestBody LocalDTO novoLocalDto) {
       Local localSalvo = localRepository.save(localAdapter.toEntity(novoLocalDto));

       LocalDTO localDto = localAdapter.toDto(localSalvo);

       return ResponseEntity.status(HttpStatus.CREATED).body(localDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> editarLocalPorId(@PathVariable Long id, @RequestBody Local novoLocal) {
        Local localSalvo = localRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));

        localSalvo.setDescricao(novoLocal.getDescricao());
        localSalvo.setPessoas(novoLocal.getPessoas());

        LocalDTO localDto = localAdapter.toDto(localRepository.save(localSalvo));

        return ResponseEntity.status(HttpStatus.OK).body(localDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocalPorId(@PathVariable Long id) {
        localRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

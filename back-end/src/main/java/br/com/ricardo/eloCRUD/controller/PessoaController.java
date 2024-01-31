package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.adapter.PessoaAdapter;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import br.com.ricardo.eloCRUD.repository.PessoaRepository;
import br.com.ricardo.eloCRUD.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/id/{id}")
    public ResponseEntity<PessoaDTO> getPessoaId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.getPessoaPorId(id));
    }

    @GetMapping("/quantidade-pessoas")
    public ResponseEntity<Long> getQuantidadePessoas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.getQuantidadePessoas());
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> getTodasPessoasNomeCpf(@RequestParam(value = "nome", required = false) String nome,
                                                                  @RequestParam(value = "cpf", required = false) String cpf,
                                                                  Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.getTodasPessoasNomeCpf(nome, cpf, pageable));
    }

    @PostMapping
    public ResponseEntity<Pessoa> postPessoa(@RequestBody @Validated Pessoa novaPessoa) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.pessoaService.savePessoa(novaPessoa));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Pessoa> putPessoa(@PathVariable Long id, @RequestBody @Validated Pessoa novaPessoa) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.putPessoa(id, novaPessoa));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        this.pessoaService.deletePessoa(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}

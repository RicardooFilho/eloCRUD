package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.adapter.PessoaAdapter;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import br.com.ricardo.eloCRUD.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaAdapter pessoaAdapter;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public  ResponseEntity<Page<PessoaDTO>> pegarTodasPessoas(Pageable pageable) {
        Page<PessoaDTO> pessoaDtoPage = pessoaRepository.findAll(pageable).map(pessoa -> pessoaAdapter.toDto(pessoa));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> pegarPesssoaPorId(@PathVariable Long id) {
        PessoaDTO pessoaDto = pessoaAdapter.toDto(pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada")));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaDto);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO novaPessoaDto) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoaAdapter.toEntity(novaPessoaDto));

        PessoaDTO pessoaDto = pessoaAdapter.toDto(pessoaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> editarPessoaPorId(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        Pessoa pessoaSalva = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoaSalva.setNome(novaPessoa.getNome());
        pessoaSalva.setCpf(novaPessoa.getCpf());
        pessoaSalva.setTelefone(novaPessoa.getTelefone());
        pessoaSalva.setEmail(novaPessoa.getEmail());

        PessoaDTO pessoaDtoSalva = pessoaAdapter.toDto(pessoaRepository.save(pessoaSalva));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaDtoSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoaPorId(@PathVariable Long id) {
        pessoaRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

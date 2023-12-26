package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public  ResponseEntity<List<Pessoa>> todasPessoas() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> pesssoaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaRepository.findById(id).orElseThrow(null));
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa novaPessoa) {
        Pessoa pessoa = pessoaRepository.save(novaPessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        Pessoa pessoaSalva = pessoaRepository.findById(id).orElseThrow(null);

        pessoaSalva.setNome(novaPessoa.getNome());
        pessoaSalva.setCpf(novaPessoa.getCpf());
        pessoaSalva.setTelefone(novaPessoa.getTelefone());
        pessoaSalva.setEmail(novaPessoa.getEmail());

        Pessoa pessoa = pessoaRepository.save(pessoaSalva);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

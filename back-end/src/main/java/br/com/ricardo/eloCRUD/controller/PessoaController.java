package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> todasPessoas() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa pesssoaPorId(@PathVariable Long id) {
        return pessoaRepository.findById(id).orElseThrow(null);
    }

    @PostMapping
    public Pessoa criarPessoa(@RequestBody Pessoa novaPessoa) {
        return pessoaRepository.save(novaPessoa);
    }

    @PutMapping("/{id}")
    public Pessoa editarPessoa(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        Pessoa pessoaSalva = pessoaRepository.findById(id).orElseThrow(null);

        pessoaSalva.setNome(novaPessoa.getNome());
        pessoaSalva.setCpf(novaPessoa.getCpf());
        pessoaSalva.setTelefone(novaPessoa.getTelefone());
        pessoaSalva.setEmail(novaPessoa.getEmail());

        return pessoaRepository.save(pessoaSalva);
    }

    @DeleteMapping("/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
    }
}

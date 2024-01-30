package br.com.ricardo.eloCRUD.service;

import br.com.ricardo.eloCRUD.adapter.PessoaAdapter;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import br.com.ricardo.eloCRUD.formatter.Formatter;
import br.com.ricardo.eloCRUD.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaAdapter pessoaAdapter;

    public Page<PessoaDTO> getTodasPessoasNomeCpf(String nome, String cpf, Pageable pageable) {
        if (Objects.nonNull(nome) || Objects.nonNull(cpf)) {
            Page<PessoaDTO> pessoaDTOPage = this.pessoaRepository.getByNomeAndCpf(nome, cpf, pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

            Formatter.formatCpfPageable(pessoaDTOPage);
            Formatter.formatTelefonePageable(pessoaDTOPage);

            return pessoaDTOPage;
        }

        Page<PessoaDTO> pessoaDTOPage = this.pessoaRepository.findAll(pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

        Formatter.formatCpfPageable(pessoaDTOPage);
        Formatter.formatTelefonePageable(pessoaDTOPage);

        return pessoaDTOPage;
    }

    public PessoaDTO getPessoaPorId(Long id) {
        PessoaDTO pessoaDTO = this.pessoaAdapter.toDto(this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada")));

        Formatter.formatCpf(pessoaDTO);
        Formatter.formatTelefone(pessoaDTO);

        return pessoaDTO;
    }

    public Long getQuantidadePessoas() {
        return this.pessoaRepository.count();
    }

    public Pessoa putPessoa(Long id, Pessoa pessoa) {
        Pessoa pessoaSalva = this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoaSalva.setNome(pessoa.getNome());
        pessoaSalva.setCpf(pessoa.getCpf());
        pessoaSalva.setTelefone(pessoa.getTelefone());
        pessoaSalva.setEmail(pessoa.getEmail());
        pessoaSalva.getLocais().clear();
        pessoaSalva.getLocais().addAll(pessoa.getLocais());

        return this.pessoaRepository.save(pessoaSalva);
    }

    public Pessoa savePessoa(Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id) {
        this.pessoaRepository.deleteById(id);
    }
}

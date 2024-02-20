package br.com.ricardo.eloCRUD.service;

import br.com.ricardo.eloCRUD.adapter.PessoaAdapter;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import br.com.ricardo.eloCRUD.exceptions.PessoaNotFoundException;
import br.com.ricardo.eloCRUD.formatter.Formatter;
import br.com.ricardo.eloCRUD.repository.PessoaRepository;
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

//            Formatter.formatCpfTelefonePageable(pessoaDTOPage);

            return pessoaDTOPage;
        }

        Page<PessoaDTO> pessoaDTOPage = this.pessoaRepository.findAll(pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

//        Formatter.formatCpfTelefonePageable(pessoaDTOPage);

        return pessoaDTOPage;
    }

    public PessoaDTO getPessoaPorId(Long id) {
        PessoaDTO pessoaDTO = this.pessoaAdapter.toDto(this.pessoaRepository.findById(id).orElseThrow(PessoaNotFoundException::new));

        Formatter.formatCpfTelefone(pessoaDTO);

        return pessoaDTO;
    }

    public Long getQuantidadePessoas() {
        return this.pessoaRepository.count();
    }

    public Pessoa putPessoa(Long id, Pessoa novaPessoa) {
        Pessoa pessoaSalva = this.pessoaRepository.findById(id).orElseThrow(PessoaNotFoundException::new);

        pessoaSalva.setNome(novaPessoa.getNome());
        pessoaSalva.setCpf(novaPessoa.getCpf());
        pessoaSalva.setTelefone(novaPessoa.getTelefone());
        pessoaSalva.setEmail(novaPessoa.getEmail());
        pessoaSalva.setDataNascimento(novaPessoa.getDataNascimento());
        pessoaSalva.setIdade(novaPessoa.getIdade());
        pessoaSalva.getLocais().clear();
        pessoaSalva.getLocais().addAll(novaPessoa.getLocais());
        pessoaSalva.getEnderecos().clear();
        pessoaSalva.getEnderecos().addAll(novaPessoa.getEnderecos());

        return this.pessoaRepository.save(pessoaSalva);
    }

    public Pessoa savePessoa(Pessoa novaPessoa) {
        return this.pessoaRepository.save(novaPessoa);
    }

    public void deletePessoa(Long id) {
        this.pessoaRepository.deleteById(id);
    }
}

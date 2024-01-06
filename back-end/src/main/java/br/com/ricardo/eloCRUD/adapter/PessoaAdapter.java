package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import org.springframework.stereotype.Service;

@Service
public class PessoaAdapter implements Adapter<PessoaDTO, Pessoa>{

    @Override
    public Pessoa toEntity(PessoaDTO pessoaDTO) {
        return new Pessoa(pessoaDTO.getId(),
                        pessoaDTO.getNome(),
                        pessoaDTO.getCpf(),
                        pessoaDTO.getTelefone(),
                        pessoaDTO.getEmail());
    }

    @Override
    public PessoaDTO toDto(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getCpf(),
                            pessoa.getTelefone(),
                            pessoa.getEmail(),
                            pessoa.getLocais());
    }
}

package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.Objects;

@Service
public class PessoaAdapter implements Adapter<PessoaDTO, Pessoa>{

    @Override
    public Pessoa toEntity(PessoaDTO pessoaDTO) {
        if (Objects.isNull(pessoaDTO)) {
            return null;
        }

        return new Pessoa(pessoaDTO.getId(),
                        pessoaDTO.getNome(),
                        pessoaDTO.getCpf(),
                        pessoaDTO.getTelefone(),
                        pessoaDTO.getEmail());
    }

    @Override
    public PessoaDTO toDto(Pessoa pessoa) {
        if (Objects.isNull(pessoa)) {
            return null;
        }

        return new PessoaDTO(pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getCpf(),
                            pessoa.getTelefone(),
                            pessoa.getEmail(),
                            pessoa.getLocais());
    }
}

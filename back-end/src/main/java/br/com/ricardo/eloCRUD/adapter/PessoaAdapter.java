package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PessoaAdapter implements Adapter<PessoaDTO, Pessoa>{

    private final LocalAdapter localAdapter;

    public PessoaAdapter(LocalAdapter localAdapter) {
        this.localAdapter = localAdapter;
    }

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

        List<LocalDTO> localDTOList = pessoa.getLocais().stream().map(local -> {
            return this.localAdapter.toDto(local);
        }).collect(Collectors.toList());

        return new PessoaDTO(pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getCpf(),
                            pessoa.getTelefone(),
                            pessoa.getEmail(),
                            localDTOList);
    }
}

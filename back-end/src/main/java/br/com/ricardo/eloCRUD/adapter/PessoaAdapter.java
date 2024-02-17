package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PessoaAdapter implements Adapter<PessoaDTO, Pessoa>{

    @Override
    public Pessoa toEntity(PessoaDTO pessoaDTO) {
        if (Objects.isNull(pessoaDTO)) {
            return null;
        }

        List<Local> locais = pessoaDTO.getLocais()
                .stream()
                .map(localDTO -> {
                    return Local.builder()
                            .id(localDTO.getId())
                            .descricao(localDTO.getDescricao())
                            .build();
                }).collect(Collectors.toList());

        return new Pessoa(pessoaDTO.getId(),
                        pessoaDTO.getNome(),
                        pessoaDTO.getCpf(),
                        pessoaDTO.getTelefone(),
                        pessoaDTO.getEmail(),
                        pessoaDTO.getDataNascimento(),
                        pessoaDTO.getIdade(),
                        locais);
    }

    @Override
    public PessoaDTO toDto(Pessoa pessoa) {
        if (Objects.isNull(pessoa)) {
            return null;
        }

        List<LocalDTO> localDTOList = pessoa.getLocais()
                .stream()
                .map(local -> {
                    return LocalDTO.builder()
                            .id(local.getId())
                            .descricao(local.getDescricao())
                            .build();
                })
                .collect(Collectors.toList());

        pessoa.setIdadePorData();

        return new PessoaDTO(pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getCpf(),
                            pessoa.getTelefone(),
                            pessoa.getEmail(),
                            pessoa.getDataNascimento(),
                            pessoa.getIdade(),
                            localDTOList);
    }
}

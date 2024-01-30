package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalAdapter implements Adapter<LocalDTO, Local> {

    @Override
    public Local toEntity(LocalDTO localDTO) {
        return new Local(localDTO.getId(),
                localDTO.getDescricao());
    }

    @Override
    public LocalDTO toDto(Local local) {
        List<PessoaDTO> pessoaDTOList = local.getPessoas()
                .stream()
                .map(pessoa -> {
                    return PessoaDTO.builder()
                            .id(pessoa.getId())
                            .nome(pessoa.getNome())
                            .cpf(pessoa.getCpf())
                            .telefone(pessoa.getTelefone())
                            .email(pessoa.getEmail())
                            .build();
                })
                .collect(Collectors.toList());

        return new LocalDTO(local.getId(),
                local.getDescricao(),
                pessoaDTOList);
    }
}

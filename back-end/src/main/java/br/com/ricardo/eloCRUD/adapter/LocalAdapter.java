package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LocalAdapter implements Adapter<LocalDTO, Local>{

    @Override
    public Local toEntity(LocalDTO localDTO) {
        return new Local(localDTO.getId(),
                        localDTO.getDescricao());
    }

    @Override
    public LocalDTO toDto(Local local) {
        return new LocalDTO(local.getId(),
                            local.getDescricao(),
                            local.getPessoas());
    }
}

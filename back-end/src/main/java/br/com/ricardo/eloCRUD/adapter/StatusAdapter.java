package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Status;
import br.com.ricardo.eloCRUD.dto.StatusDTO;
import org.springframework.stereotype.Service;

@Service
public class StatusAdapter implements Adapter<StatusDTO, Status> {

    @Override
    public Status toEntity(StatusDTO statusDTO) {
        return new Status(statusDTO.getId(),
                        statusDTO.getDescricao(),
                        statusDTO.getSituacao());
    }

    @Override
    public StatusDTO toDto(Status status) {
        return new StatusDTO(status.getId(),
                            status.getDescricao(),
                            status.getSituacao());
    }
}

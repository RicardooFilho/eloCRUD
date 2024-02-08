package br.com.ricardo.eloCRUD.service;

import br.com.ricardo.eloCRUD.adapter.StatusAdapter;
import br.com.ricardo.eloCRUD.domain.Status;
import br.com.ricardo.eloCRUD.dto.StatusDTO;
import br.com.ricardo.eloCRUD.exceptions.StatusNotFoundException;
import br.com.ricardo.eloCRUD.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StatusAdapter statusAdapter;

    public Page<StatusDTO> getTodosStatusDescricao(String descricao, Pageable pageable) {
        if (Objects.nonNull(descricao)) {
            return this.statusRepository.getStatusByDescricao(descricao, pageable).map(status -> this.statusAdapter.toDto(status));
        }

        return this.statusRepository.findAll(pageable).map(status -> this.statusAdapter.toDto(status));
    }

    public StatusDTO getStatusPorId(Long id) {
        return this.statusAdapter.toDto(this.statusRepository.findById(id).orElseThrow(StatusNotFoundException::new));
    }

    public Long getQuantidadeStatus() {
        return this.statusRepository.count();
    }

    public Status putStatus(Long id, Status novoStatus) {
        Status statusSalvo = this.statusRepository.findById(id).orElseThrow(StatusNotFoundException::new);

        statusSalvo.setDescricao(novoStatus.getDescricao());
        statusSalvo.setSituacao(novoStatus.getSituacao());

        return this.statusRepository.save(statusSalvo);
    }

    public Status saveStatus(Status novoStatus) {
        return this.statusRepository.save(novoStatus);
    }

    public void deleteStatus (Long id) {
        this.statusRepository.deleteById(id);
    }
}

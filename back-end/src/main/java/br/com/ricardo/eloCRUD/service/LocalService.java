package br.com.ricardo.eloCRUD.service;

import br.com.ricardo.eloCRUD.adapter.LocalAdapter;
import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.dto.LocalDTO;
import br.com.ricardo.eloCRUD.exceptions.LocalNotFoundException;
import br.com.ricardo.eloCRUD.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LocalService {

    @Autowired
    private LocalAdapter localAdapter;

    @Autowired
    private LocalRepository localRepository;

    public Page<LocalDTO> getTodosLocaisDescricao(String descricao, Pageable pageable) {
        if (Objects.nonNull(descricao)) {
            return this.localRepository.getLocalByDescricao(descricao, pageable).map(local -> this.localAdapter.toDto(local));
        }

        return this.localRepository.findAll(pageable).map(local -> this.localAdapter.toDto(local));
    }

    public LocalDTO getLocalPorId(Long id) {
        return this.localAdapter.toDto(this.localRepository.findById(id).orElseThrow(LocalNotFoundException::new));
    }

    public Long getQuantidadeLocais() {
        return this.localRepository.count();
    }

    public Local putLocal(Long id, Local novoLocal) {
        Local localSalvo = this.localRepository.findById(id).orElseThrow(LocalNotFoundException::new);

        localSalvo.setDescricao(novoLocal.getDescricao());
        localSalvo.getPessoas().clear();
        localSalvo.getPessoas().addAll(novoLocal.getPessoas());

        return this.localRepository.save(localSalvo);
    }

    public Local saveLocal(Local novoLocal) {
        return this.localRepository.save(novoLocal);
    }

    public void deleteLocal(Long id) {
        this.localRepository.deleteById(id);
    }
}

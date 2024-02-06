package br.com.ricardo.eloCRUD.service;

import br.com.ricardo.eloCRUD.adapter.CategoriaAdapter;
import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.dto.CategoriaDTO;
import br.com.ricardo.eloCRUD.exceptions.CategoriaNotFoundException;
import br.com.ricardo.eloCRUD.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaAdapter categoriaAdapter;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<CategoriaDTO> getTodasCategoriasDescricao(String descricao, Pageable pageable) {
        if (Objects.nonNull(descricao)) {
            return this.categoriaRepository.getCategoriaByDescricao(descricao, pageable).map(categoria -> this.categoriaAdapter.toDto(categoria));
        }

        return this.categoriaRepository.findAll(pageable).map(categoria -> this.categoriaAdapter.toDto(categoria));
    }

    public CategoriaDTO getCategoriaPorId(Long id) {
        return this.categoriaAdapter.toDto(this.categoriaRepository.findById(id).orElseThrow(CategoriaNotFoundException::new));
    }

    public Long getQuantidadeCategorias() {
        return this.categoriaRepository.count();
    }

    public Categoria putCategoria(Long id, Categoria novaCategoria) {
        Categoria categoriaSalva = this.categoriaRepository.findById(id).orElseThrow(CategoriaNotFoundException::new);

        categoriaSalva.setDescricao(novaCategoria.getDescricao());

        return this.categoriaRepository.save(categoriaSalva);
    }

    public Categoria saveCategoria(Categoria novaCategoria) {
        return this.categoriaRepository.save(novaCategoria);
    }

    public void deleteCategoria(Long id) {
        this.categoriaRepository.deleteById(id);
    }
}

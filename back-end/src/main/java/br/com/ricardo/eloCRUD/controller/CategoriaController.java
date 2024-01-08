package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.adapter.CategoriaAdapter;
import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.dto.CategoriaDTO;
import br.com.ricardo.eloCRUD.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaAdapter categoriaAdapter;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> pegarTodasCategorias(Pageable pageable) {
        Page<CategoriaDTO> categoriaDtoPage = categoriaRepository.findAll(pageable).map(categoria -> categoriaAdapter.toDto(categoria));

        return ResponseEntity.status(HttpStatus.OK).body(categoriaDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> pegarCategoriaPorId(@PathVariable Long id) {
        CategoriaDTO categoriaDto = categoriaAdapter.toDto(categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada")));

        return ResponseEntity.status(HttpStatus.OK).body(categoriaDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaDTO novaCategoriaDto) {
        Categoria categoriaSalva = categoriaRepository.save(categoriaAdapter.toEntity(novaCategoriaDto));

        CategoriaDTO categoriaDto = categoriaAdapter.toDto(categoriaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> editarCategoriaPorId(@PathVariable Long id, @RequestBody Categoria novaCategoria) {
        Categoria categoriaSalva = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        categoriaSalva.setDescricao(novaCategoria.getDescricao());

        CategoriaDTO categoriaDto = categoriaAdapter.toDto(categoriaRepository.save(categoriaSalva));

        return ResponseEntity.status(HttpStatus.OK).body(categoriaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoriaPorId(@PathVariable Long id) {
        categoriaRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

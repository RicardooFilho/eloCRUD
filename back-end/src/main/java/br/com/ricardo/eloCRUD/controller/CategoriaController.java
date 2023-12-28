package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> pegarTodasCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> pegarCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaRepository.findById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria novaCategoria) {
        Categoria categoria = categoriaRepository.save(novaCategoria);

        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoriaPorId(@PathVariable Long id, @RequestBody Categoria novaCategoria) {
        Categoria categoriaSalva = categoriaRepository.findById(id).orElseThrow(null);

        categoriaSalva.setDescricao(novaCategoria.getDescricao());

        return ResponseEntity.ok(categoriaRepository.save(categoriaSalva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoriaPorId(@PathVariable Long id) {
        categoriaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

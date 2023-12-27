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
        List<Categoria> categorias = categoriaRepository.findAll();

        if (categorias.isEmpty()) {
                    new ResponseEntity<>(categorias, HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(categorias);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> pegarCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria novaCategoria) {
        Categoria categoria = categoriaRepository.save(novaCategoria);

        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Long id, @RequestBody Categoria novaCategoria) {
        Categoria categoriaSalva = categoriaRepository.findById(id).orElseThrow(null);

        categoriaSalva.setDescricao(novaCategoria.getDescricao());

        Categoria categoria = categoriaRepository.save(categoriaSalva);

        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria (@PathVariable Long id) {
        categoriaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

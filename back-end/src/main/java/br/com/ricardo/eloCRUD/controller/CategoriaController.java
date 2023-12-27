package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> pegarTodasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();

        if (categorias.isEmpty()) {
            return new ResponseEntity<>(categorias, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categorias, HttpStatus.OK);
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
}

package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.dto.CategoriaDTO;
import br.com.ricardo.eloCRUD.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> getTodasCategoriasDescricao(@RequestParam(value = "descricao", required = false) String descricao,
                                                                          Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.categoriaService.getTodasCategoriasDescricao(descricao, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.categoriaService.getCategoriaPorId(id));
    }

    @GetMapping("/quantidade-categorias")
    public ResponseEntity<Long> getQuantidadeCategorias() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.categoriaService.getQuantidadeCategorias());
    }

    @PostMapping
    public ResponseEntity<Categoria> postCategoria(@RequestBody @Validated Categoria novaCategoria) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.categoriaService.saveCategoria(novaCategoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoriaPorId(@PathVariable Long id, @RequestBody @Validated Categoria novaCategoria) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.categoriaService.putCategoria(id, novaCategoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}

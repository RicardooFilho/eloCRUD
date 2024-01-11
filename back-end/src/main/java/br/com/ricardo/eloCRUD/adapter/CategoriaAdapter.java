package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.dto.CategoriaDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoriaAdapter implements Adapter<CategoriaDTO, Categoria>{

    @Override
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(),
                            categoriaDTO.getDescricao());
    }

    @Override
    public CategoriaDTO toDto(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(),
                                categoria.getDescricao());
    }
}

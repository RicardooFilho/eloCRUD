package br.com.ricardo.eloCRUD.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalDTO {

    private Long id;

    private String descricao;

    private List<PessoaDTO> pessoas;

    public LocalDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}

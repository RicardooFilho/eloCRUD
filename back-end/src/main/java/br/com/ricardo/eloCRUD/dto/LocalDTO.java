package br.com.ricardo.eloCRUD.dto;

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
}

package br.com.ricardo.eloCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalDTO {

    private Long id;

    private String descricao;

    private List<PessoaDTO> pessoas = new ArrayList<PessoaDTO>();
}

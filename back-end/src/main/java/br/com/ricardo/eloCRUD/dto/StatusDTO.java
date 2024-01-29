package br.com.ricardo.eloCRUD.dto;

import br.com.ricardo.eloCRUD.enums.SituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

    private Long id;

    private String descricao;

    private SituacaoEnum situacao;
}

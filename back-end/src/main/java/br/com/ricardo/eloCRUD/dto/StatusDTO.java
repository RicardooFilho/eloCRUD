package br.com.ricardo.eloCRUD.dto;

import br.com.ricardo.eloCRUD.enums.SituacaoEnum;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

    private Long id;

    @NotBlank(message = "Insira uma descrição")
    private String descricao;

    @Nonnull
    private SituacaoEnum situacao;
}

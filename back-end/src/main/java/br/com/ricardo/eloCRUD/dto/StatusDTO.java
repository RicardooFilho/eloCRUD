package br.com.ricardo.eloCRUD.dto;

import br.com.ricardo.eloCRUD.enums.SituacaoEnum;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

    private Long id;

    @NotBlank(message = "Insira uma descrição")
    private String descricao;

    @NotNull
    private SituacaoEnum situacao;
}

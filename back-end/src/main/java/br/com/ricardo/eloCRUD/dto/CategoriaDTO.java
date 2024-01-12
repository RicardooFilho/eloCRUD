package br.com.ricardo.eloCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "Insira uma descrição")
    private String descricao;
}

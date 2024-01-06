package br.com.ricardo.eloCRUD.dto;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalDTO {

    private Long id;

    @NotBlank(message = "Insira uma descrição")
    private String descricao;

    private List<Pessoa> pessoas;

}

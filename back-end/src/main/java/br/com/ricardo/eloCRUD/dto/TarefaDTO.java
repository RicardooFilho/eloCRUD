package br.com.ricardo.eloCRUD.dto;

import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.domain.Status;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    @Nonnull
    private Long numero;

    @NotNull
    private Integer exercicio;

    @Nonnull
    private PessoaDTO requerenteDto;

    @NotBlank(message = "Insira um título")
    private String titulo;

    @Nonnull
    private CategoriaDTO categoriaDto;

    @NotBlank(message = "Insira uma descrição")
    private String descricao;

    @Nonnull
    private PessoaDTO requeridoDto;

    @Nonnull
    private LocalDTO localDestinoDto;

    @Nonnull
    private LocalDate dataCriacao;

    @Nonnull
    private StatusDTO statusDto;

}

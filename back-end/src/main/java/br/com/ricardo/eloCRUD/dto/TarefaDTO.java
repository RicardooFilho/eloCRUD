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

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    @Nonnull
    private Long numero;

    @Nonnull
    private Integer exercicio;

    @Nonnull
    private Pessoa requerente;

    @NotBlank(message = "Insira um título")
    private String titulo;

    @Nonnull
    private Categoria categoria;

    @NotBlank(message = "Insira uma descrição")
    private String descricao;

    @Nonnull
    private Pessoa requerido;

    @Nonnull
    private Local localDestino;

    @Nonnull
    private LocalDate dataCriacao;

    @Nonnull
    private Status status;

}

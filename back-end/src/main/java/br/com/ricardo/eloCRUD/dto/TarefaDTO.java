package br.com.ricardo.eloCRUD.dto;

import br.com.ricardo.eloCRUD.domain.Categoria;
import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    private Long numero;

    private Integer exercicio;

    private Pessoa requerente;

    private String titulo;

    private Categoria categoria;

    private String descricao;

    private Pessoa requerido;

    private Local localDestino;

    private LocalDate dataCriacao;

    private Status status;

}

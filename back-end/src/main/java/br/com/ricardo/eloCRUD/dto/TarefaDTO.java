package br.com.ricardo.eloCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    private Long numero;

    private Integer exercicio;

    private PessoaDTO requerenteDto;

    private String titulo;

    private CategoriaDTO categoriaDto;

    private String descricao;

    private PessoaDTO requeridoDto;

    private LocalDTO localDestinoDto;

    private Timestamp dataCriacao;

    private StatusDTO statusDto;

}

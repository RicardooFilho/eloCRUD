package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "comentario")
@Data
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_comentario")
    @SequenceGenerator(name = "gen_comentario", sequenceName = "comentario_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "Insira uma descrição")
    @Length(message = "Descrição deve conter no máximo 200 caracteres", max = 200)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "data_comentario", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "tarefa_numero", referencedColumnName = "numero", nullable = false)
    private Tarefa tarefa;
}

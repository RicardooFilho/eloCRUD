package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "comentario")
@Data
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_comentario")
    @SequenceGenerator(name = "gen_comentario", sequenceName = "comentario_sequence", initialValue = 1)
    private Long id;

    @Column(length = 200, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoaId;

    @Column(name = "data_comentario", nullable = false)
    private LocalDate dataComentario;

    @ManyToOne
    @JoinColumn(name = "tarefa_numero", referencedColumnName = "numero", nullable = false)
    private Tarefa tarefaNumero;
}

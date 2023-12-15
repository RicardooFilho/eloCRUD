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

    @Column(nullable = false)
    private Pessoa pessoaId;

    @Column(nullable = false)
    private LocalDate dataComentario;

    @ManyToOne
    @JoinColumn(name = "tarefa_numero", referencedColumnName = "numero", nullable = false)
//    @Column(name = "tarefa_id")
    private Tarefa tarefaNumero;
}

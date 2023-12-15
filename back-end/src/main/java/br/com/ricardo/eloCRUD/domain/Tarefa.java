package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tarefa")
@Data
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_tarefa")
    @SequenceGenerator(name = "gen_tarefa", sequenceName = "tarefa_sequence", initialValue = 1)
    private Long numero;

    @Column(nullable = false)
    private Integer exercicio;

    @ManyToOne
    @JoinColumn(name = "requerente_id", referencedColumnName = "id", nullable = false)
//    @Column(name = "requerente_id", nullable = false)
    private Pessoa requerenteId;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
//    @Column(name = "categoria_id", nullable = false)
    private Categoria categoriaId;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "requerido_id", referencedColumnName = "id", nullable = false)
//    @Column(name = "requerido_id", nullable = false)
    private Pessoa requeridoId;

    @ManyToOne
    @JoinColumn(name = "local_destino_id", referencedColumnName = "id", nullable = false)
//    @Column(name = "local_destino_id", nullable = false)
    private Local localDestinoId;

    @Column(name = "comentario_id")
    private Comentario comentarioId;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
//    @Column(name = "status_id", nullable = false)
    private Status statusId;
}

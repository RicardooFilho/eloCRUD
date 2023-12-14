package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "status")
@Data
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_status")
    @SequenceGenerator(name = "gen_status", sequenceName = "status_sequence", initialValue = 1)
    private Long id;

    @Column(length = 30, nullable = false)
    private String descricao;

    @Column(nullable = false)
    private SituacaoEnum situacao;

    @OneToMany(mappedBy = "statusId")
    private List<Tarefa> tarefas;
}

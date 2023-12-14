package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_categoria")
    @SequenceGenerator(name = "gen_categoria", sequenceName = "categoria_sequence", initialValue = 1)
    private Long id;

    @Column(length = 20, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "categoriaId")
    private List<Tarefa> tarefas;
}

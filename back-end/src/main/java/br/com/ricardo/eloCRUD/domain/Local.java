package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "local")
@Data
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_local")
    @SequenceGenerator(sequenceName = "local_sequence", initialValue = 1, name = "gen_local")
    private Long id;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "locais")
    private List<Pessoa> pessoas;
}

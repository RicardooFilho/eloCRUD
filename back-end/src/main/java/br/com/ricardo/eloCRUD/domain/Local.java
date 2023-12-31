package br.com.ricardo.eloCRUD.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "local")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_local")
    @SequenceGenerator(sequenceName = "local_sequence", initialValue = 1, name = "gen_local", allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "locais")
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public Local(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}

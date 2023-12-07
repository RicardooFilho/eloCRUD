package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "locais")
@Data
public class Local {

    @Id
    private Long id;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "locais")
    private List<Pessoa> pessoas;
}

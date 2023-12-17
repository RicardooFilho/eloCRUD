package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_categoria")
    @SequenceGenerator(name = "gen_categoria", sequenceName = "categoria_sequence", initialValue = 1)
    private Long id;

    @Column(length = 20, nullable = false)
    private String descricao;
}

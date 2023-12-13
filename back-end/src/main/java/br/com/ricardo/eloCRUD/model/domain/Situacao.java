package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "situacao")
@Data
public class Situacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_situacao")
    @SequenceGenerator(name = "gen_situacao", sequenceName = "situacao_sequence", initialValue = 1)
    private Long id;

    @Column(length = 50, nullable = false)
    private String descricao;
}

package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PESSOA")
@Data
@Getter
@Setter
public class Pessoa {

    @Id
    private Long id;

    private String nome;

    private Integer idade;

    private String sobrenome;
}

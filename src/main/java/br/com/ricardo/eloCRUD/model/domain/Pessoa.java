package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PESSOA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    private Long id;

    @Column(length = 65)
    private String nome;

    @Column(length = 12)
    private String cpf;

    @Column(length = 12)
    private String telefone;

    @Column(length = 60)
    private String email;
}

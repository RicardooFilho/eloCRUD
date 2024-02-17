package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "local")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_local")
    @SequenceGenerator(sequenceName = "local_sequence", initialValue = 1, name = "gen_local", allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Insira uma descrição")
    @Length(message = "Descrição deve conter no máximo 50 caracteres", max = 50)
    private String descricao;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "locais")
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
}

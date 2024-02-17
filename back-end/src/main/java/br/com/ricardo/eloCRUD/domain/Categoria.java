package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_categoria")
    @SequenceGenerator(name = "gen_categoria", sequenceName = "categoria_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "Insira uma descrição")
    @Length(message = "Descrição deve conter no máximo 20 caracteres", max = 20)
    private String descricao;

    public Categoria(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}

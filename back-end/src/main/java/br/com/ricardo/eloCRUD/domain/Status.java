package br.com.ricardo.eloCRUD.domain;

import br.com.ricardo.eloCRUD.enums.SituacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "status")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_status")
    @SequenceGenerator(name = "gen_status", sequenceName = "status_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Insira uma descrição")
    @Length(message = "Descrição deve conter no máxiomo 30 caracteres", max = 30)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Insira uma situação")
    private SituacaoEnum situacao;

    public Status (Long id, String descricao, SituacaoEnum situacao) {
        this.id = id;
        this.descricao = descricao;
        this.situacao = situacao;
    }
}

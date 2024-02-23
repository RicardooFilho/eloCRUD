package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_endereco")
    @SequenceGenerator(sequenceName = "endereco_sequence", name = "gen_pessoa", initialValue = 1, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Informe o CEP")
    @Column(name = "cep", length = 8, nullable = false)
    @Length(message = "CEP não pode ter mais/menos de 8 número", min = 8, max = 8)
    private String cep;

    @NotBlank(message = "Informe o logradouro")
    @Column(name = "logradouro", length = 100, nullable = false)
    @Length(message = "Logradouro pode ter no máximo 100 caracteres", min = 1, max = 100)
    private String logradouro;

    @NotBlank(message = "Informe o bairro")
    @Column(name = "bairro", length = 50, nullable = false)
    @Length(message = "Bairro pode ter no máximo 50 caracteres", min = 1, max = 50)
    private String bairro;

    @Column(name = "complemento", length = 30, nullable = true)
    @Length(message = "Complemento pode ter no máximo 30 caracteres", min = 1, max = 30)
    private String complemento;

    @NotBlank(message = "Informe a cidade")
    @Column(name = "cidade", length = 40, nullable = false)
    @Length(message = "Cidade pode ter no máximo 40 caracteres", min = 1, max = 40)
    private String cidade;

    @NotBlank(message = "Informe a unidade federativa")
    private String uf;
}

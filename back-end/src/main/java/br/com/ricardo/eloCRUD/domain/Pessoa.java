package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pessoa")
    @SequenceGenerator(sequenceName = "pessoa_sequence", name = "gen_pessoa", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(length = 254, nullable = false)
    @NotBlank(message = "Insira um nome")
    @Length(message = "Nome deve conter no máximo 254 caracteres", max = 254)
    private String nome;

    @Column(length = 11, nullable = false)
    @NotBlank(message = "Insira um CPF")
    @CPF(message = "CPF inválido")
    @Length(message = "CPF deve conter apenas 11 números", min = 11, max = 11)
    private String cpf;

    @Column(length = 11, nullable = false)
    @NotBlank(message = "Insira um telefone")
    @Length(message = "Telefone deve conter apenas 11 números", min = 11, max = 11)
    private String telefone;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Insira um e-mail")
    @Email(message = "E-mail inválido")
    @Length(message = "Email deve conter no máximo 100 caracteres", max = 100)
    private String email;

    @PastOrPresent(message = "Data nascimento não pode ser uma data futura")
    @Column(name = "data_nascimento", nullable = false)
    @NotNull(message = "Insira sua data de nascimento")
    private LocalDate dataNascimento;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pessoa_local",
            joinColumns = @JoinColumn(name = "pessoa_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "local_id", referencedColumnName = "id"))
    private List<Local> locais = new ArrayList<Local>();
    //.
}

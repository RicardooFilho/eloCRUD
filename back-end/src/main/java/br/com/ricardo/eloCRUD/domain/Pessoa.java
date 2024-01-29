package br.com.ricardo.eloCRUD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pessoa")
    @SequenceGenerator(sequenceName = "pessoa_sequence", name = "gen_pessoa", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(length = 254, nullable = false)
    @NotBlank(message = "Insira um nome")
    private String nome;

    @Column(length = 11, nullable = false)
    @NotBlank(message = "Insira um CPF")
    @CPF(message = "CPF inválido")
    @Size(message = "CPF deve conter apenas 11 números", min = 11, max = 11)
    private String cpf;

    @Column(length = 11, nullable = false)
    @NotBlank(message = "Insira um telefone")
    @Size(message = "Telefone deve conter apenas 11 números", min = 11, max = 11)
    private String telefone;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Insira um e-mail")
    @Email(message = "E-mail inválido")
    private String email;

    @ManyToMany
    @JoinTable(name = "pessoa_local",
            joinColumns = @JoinColumn(name = "pessoa_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "local_id", referencedColumnName = "id"))
    private List<Local> locais = new ArrayList<Local>();

    public Pessoa(Long id, String nome, String cpf, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }
}

package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pessoa")
    @SequenceGenerator(sequenceName = "pessoa_sequence", name = "gen_pessoa", initialValue = 1)
    private Long id;

    @Column(length = 254, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 11, nullable = false)
    private String telefone;

    @Column(length = 100, nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(name = "pessoa_local",
            joinColumns = @JoinColumn(name = "pessoa_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "local_id", referencedColumnName = "id"))
    private List<Local> locais;

    @OneToMany(mappedBy = "requerenteId")
    private List<Tarefa> tarefasCriadas;

    @OneToMany(mappedBy = "requeridoId")
    private List<Tarefa> tarefasRequeridas;
}

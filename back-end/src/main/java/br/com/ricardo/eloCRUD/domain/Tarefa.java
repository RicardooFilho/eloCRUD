package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "tarefa")
@Data
@EqualsAndHashCode(of = "numero")
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_tarefa")
    @SequenceGenerator(name = "gen_tarefa", sequenceName = "tarefa_sequence", initialValue = 1, allocationSize = 1)
    private Long numero;

    @Column(length = 4, nullable = false)
    @NotNull(message = "Insira um exercício")
    @Size(message = "Exercício inválido", min = 4, max = 4)
    private Integer exercicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requerente_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Insira um requerente")
    private Pessoa requerente;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Insira um título")
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    @Column(length = 1500, nullable = false)
    @NotBlank(message = "Insira uma descrição")
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requerido_id", referencedColumnName = "id", nullable = true)
    private Pessoa requerido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_destino_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Insira um local de destino")
    private Local localDestino;

    @Column(name = "data_criacao", nullable = false)
    @NotNull(message = "Insira uma data")
    @PastOrPresent(message = "A data de criação não pode ser uma data futura")
    private LocalDate dataCriacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Insira um status")
    private Status status;

    public Tarefa(Long numero, Integer exercicio, Pessoa requerente, String titulo, Categoria categoria,
                  String descricao, Pessoa requerido, Local localDestino, LocalDate dataCriacao, Status status) {
        this.numero = numero;
        this.exercicio = exercicio;
        this.requerente = requerente;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.requerido = requerido;
        this.localDestino = localDestino;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }
}

package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Year;

@Entity
@Table(name = "tarefa")
@Data
public class Tarefa {

    @Id
    private Long id;

    private Year exercicio;

    private Pessoa requerenteId;

    private String titulo;

    private Categoria categoriaId;

    private String descricao;

    private Pessoa requeridoId;

    private Local localDestinoId;
}

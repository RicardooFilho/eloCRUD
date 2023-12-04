package br.com.ricardo.eloCRUD.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Entity
@Table(name = "USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private Long id;

    private String nome;

    private String sobreonome;

    private String cpf;

    private ArrayList<Tarefa> listaDeTarefas;
}

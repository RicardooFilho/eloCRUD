package br.com.ricardo.eloCRUD.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Builder
public class Endereco {

    @Id
    private Long id;

    @NotBlank(message = "Informe o CEP")
    private String cep;

    @NotBlank(message = "Informe o logradouro")
    private String logradouro;

    @NotBlank(message = "Informe o bairro")
    private String bairro;

    private String complemento;

    @NotBlank(message = "Informe a cidade")
    private String cidade;

    @NotBlank(message = "Informe a unidade federativa")
    private String uf;
}

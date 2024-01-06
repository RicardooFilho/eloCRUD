package br.com.ricardo.eloCRUD.dto;

import br.com.ricardo.eloCRUD.domain.Local;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;

    @NotBlank(message = "Insira um nome")
    private String nome;

    @NotBlank(message = "Insira um CPF")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Insira um telefone")
    private String telefone;

    @NotBlank(message = "Insira um e-mail")
    @Email(message = "E-mail inválido")
    private String email;

    private List<Local> locais;
}

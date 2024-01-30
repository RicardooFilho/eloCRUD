package br.com.ricardo.eloCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

    private List<LocalDTO> locais = new ArrayList<LocalDTO>();
}

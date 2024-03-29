package br.com.ricardo.eloCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    private LocalDate dataNascimento;

    private Integer idade;

    private List<LocalDTO> locais = new ArrayList<LocalDTO>();

    private List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();
}

package br.com.ricardo.eloCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnderecoDTO {

    private Long id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String complemento;

    private String cidade;

    private String uf;
}

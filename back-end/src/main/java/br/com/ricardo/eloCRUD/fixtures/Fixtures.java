package br.com.ricardo.eloCRUD.fixtures;

import br.com.ricardo.eloCRUD.dto.LocalDTO;
import br.com.ricardo.eloCRUD.dto.PessoaDTO;

import java.util.List;

public class Fixtures {
    public static PessoaDTO criaPessoaDto() {
        return PessoaDTO.builder()
                .id(1L)
                .nome("Ricardo")
                .cpf("60877004048")
                .telefone("44978554126")
                .email("teste@gmail.com")
                .locais(List.of(new LocalDTO().builder()
                        .id(1L)
                        .descricao("Secretária de Saúde")
                        .build()))
                .build();
    }
}

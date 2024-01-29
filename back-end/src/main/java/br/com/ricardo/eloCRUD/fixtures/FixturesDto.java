package br.com.ricardo.eloCRUD.fixtures;

import br.com.ricardo.eloCRUD.dto.PessoaDTO;

public class FixturesDto {

    public static PessoaDTO criaPessoaDto() {
        PessoaDTO pessoaDto = new PessoaDTO(1L,
                                            "Ricardo",
                                            "46475296025",
                                            "44978554126",
                                            "teste@teste.com");

        return pessoaDto;
    }
}

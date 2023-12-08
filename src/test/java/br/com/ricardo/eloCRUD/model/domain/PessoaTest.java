package br.com.ricardo.eloCRUD.model.domain;

import br.com.ricardo.eloCRUD.exceptions.CpfInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PessoaTest {

    @Test
    public void criacaoDePessoaTest() {
        Pessoa pessoa = new Pessoa();

        pessoa.setId(1L);
        pessoa.setNome("Ricardo");
        pessoa.setCpf("46477850587");
        pessoa.setTelefone("44974001238");
        pessoa.setEmail("teste@teste.com");

        Assertions.assertEquals(1L, pessoa.getId());
        Assertions.assertEquals("Ricardo", pessoa.getNome());
        Assertions.assertEquals("46477850587", pessoa.getCpf());
        Assertions.assertEquals("44974001238", pessoa.getTelefone());
        Assertions.assertEquals("teste@teste.com", pessoa.getEmail());
    }

    @Test
    public void cpfInvalidoTest() throws CpfInvalidoException {
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf("");
        } catch (CpfInvalidoException cpfInvalidoException) {
            Assertions.assertEquals("O CPF inserido é inválido", cpfInvalidoException.getMessage());
        }
    }
}

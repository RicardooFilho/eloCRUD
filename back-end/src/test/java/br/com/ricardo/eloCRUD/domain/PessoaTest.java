package br.com.ricardo.eloCRUD.domain;

import br.com.ricardo.eloCRUD.exceptions.CpfInvalidoException;
import br.com.ricardo.eloCRUD.exceptions.CpfNullException;
import br.com.ricardo.eloCRUD.formatter.Formatter;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

public class PessoaTest {

    @Test
    public void criacaoDePessoaTest() {
        Pessoa pessoa = new Pessoa();

        pessoa.setId(1L);
        pessoa.setNome("Ricardo");
        pessoa.setCpf("46477850587");
        pessoa.setTelefone("44974001238");
        pessoa.setEmail("teste@teste.com");

        assertThat(pessoa.getId()).isEqualTo(1L);
        assertThat(pessoa.getNome()).isEqualTo("Ricardo");
        assertThat(pessoa.getCpf()).isEqualTo("46477850587");
        assertThat(pessoa.getTelefone()).isEqualTo("44974001238");
        assertThat(pessoa.getEmail()).isEqualTo("teste@teste.com");
    }
}
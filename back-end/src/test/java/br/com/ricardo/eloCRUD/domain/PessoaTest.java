package br.com.ricardo.eloCRUD.domain;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.exceptions.CpfInvalidoException;
import br.com.ricardo.eloCRUD.exceptions.CpfNullException;
import br.com.ricardo.eloCRUD.formatters.Formatter;
import br.com.ricardo.eloCRUD.validators.Validator;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void cpfFormatadoTest() throws ParseException {
        Pessoa pessoa = new Pessoa();

        pessoa.setCpf("46477850848");

        String cpfFormatado = Formatter.formatCpf(pessoa.getCpf(), "AAA.AAA.AAA-AA");

        assertThat(cpfFormatado).isEqualTo("464.778.508-48");
    }

    @Test
    public void cpfValidadoTest() throws Exception{
        try {
            Pessoa pessoa = new Pessoa();

            pessoa.setCpf(null);

            Validator.validateCpf(pessoa.getCpf());
        } catch (CpfNullException cpfNullException) {
            assertThat(cpfNullException.getMessage()).isEqualTo("Informe um CPF válido");
        }
    }

    @Test
    public void cpfInvalidoTest() throws Exception{
        try {
            Pessoa pessoa = new Pessoa();

            pessoa.setCpf("496.884.965.587");

            String cpfFormatado = Formatter.unformatCpf(pessoa.getCpf());
            Validator.validateCpf(cpfFormatado);
        } catch (CpfInvalidoException cpfInvalidoException) {
            assertThat(cpfInvalidoException.getMessage()).isEqualTo("O CPF inserido é inválido");
        }
    }
}
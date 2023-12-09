package br.com.ricardo.eloCRUD.model.domain;

import br.com.ricardo.eloCRUD.exceptions.CpfInvalidoException;
import br.com.ricardo.eloCRUD.exceptions.CpfNullException;
import br.com.ricardo.eloCRUD.formatters.Formatter;
import br.com.ricardo.eloCRUD.validators.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

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
    public void cpfFormatadoTest() throws CpfInvalidoException, ParseException {
        Pessoa pessoa = new Pessoa();

        pessoa.setCpf("46477850842");

        String cpfFormatado = Formatter.formatCpf(pessoa.getCpf(), "AAA.AAA.AAA-AA");

        Assertions.assertEquals("464.778.508-42", cpfFormatado);
    }

    @Test
    public void cpfValidadoTest() throws Exception{
        try {
            Pessoa pessoa = new Pessoa();

            pessoa.setCpf(null);

            Validator.validateCpf(pessoa.getCpf());
        } catch (CpfNullException cpfNullException) {
            Assertions.assertEquals("Informe um CPF válido", cpfNullException.getMessage());
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
            Assertions.assertEquals("O CPF inserido é inválido", cpfInvalidoException.getMessage());
        }
    }
}

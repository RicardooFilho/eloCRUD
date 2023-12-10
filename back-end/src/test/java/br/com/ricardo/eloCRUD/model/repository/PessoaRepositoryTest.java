package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("dev")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void buscaPessoaPorNomeStartingWithTest() {
        Pessoa pessoa = new Pessoa();
        Pessoa pessoa2 = new Pessoa();

        pessoa.setId(1L);
        pessoa.setNome("João Henrique");
        pessoa.setCpf("46485296345");
        pessoa.setTelefone("44974558893");
        pessoa.setEmail("teste@teste.com");

        pessoa2.setId(2L);
        pessoa2.setNome("Ricardo Francisco");
        pessoa2.setCpf("46485296345");
        pessoa2.setTelefone("44974558893");
        pessoa2.setEmail("teste@teste.com");

        pessoaRepository.save(pessoa);
        pessoaRepository.save(pessoa2);

        List<Pessoa> pessoas = pessoaRepository.findByNomeStartingWith("Jo");

        Assertions.assertEquals(1, pessoas.size());
    }
}

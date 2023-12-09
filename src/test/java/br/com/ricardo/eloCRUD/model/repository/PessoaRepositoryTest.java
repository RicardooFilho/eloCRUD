package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void buscaPessoaPorNomeStartingWithTest() {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("João Henrique");
        pessoa.setCpf("46485296345");
        pessoa.setTelefone("44974558893");
        pessoa.setEmail("teste@teste.com");

        pessoaRepository.save(pessoa);

        List<Pessoa> pessoas = pessoaRepository.findByNomeStartingWith("Jo");

        Assertions.assertEquals(1, pessoas.size());
    }
}

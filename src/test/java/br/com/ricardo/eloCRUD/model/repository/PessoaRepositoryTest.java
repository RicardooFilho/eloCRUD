package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest



@ActiveProfiles(value = "test")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testaInsertDePessoa() {
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

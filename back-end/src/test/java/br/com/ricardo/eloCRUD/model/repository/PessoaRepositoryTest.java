package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Pessoa;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("dev")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    /*private Pessoa pessoa;

    @BeforeEach
    public void setup() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João Henrique");
        pessoa.setCpf("46485296345");
        pessoa.setTelefone("44974558893");
        pessoa.setEmail("teste@teste.com");
    } */

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

        Assertions.assertThat(pessoas.size()).isEqualTo(1);
    }

    @Test
    public void InsertDePessoaTest() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Ricardo");
        pessoa.setCpf("78945612310");
        pessoa.setTelefone("44567880098");
        pessoa.setEmail("teste@teste.com");

        Pessoa pessoaInserida = pessoaRepository.save(pessoa);

        Assertions.assertThat(testEntityManager
                                .find(Pessoa.class, pessoaInserida.getId()) )
                                .isEqualTo(pessoa);
    }

    @Test
    public void UpdateDePessoaTest() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Ricardo");
        pessoa.setCpf("78945612310");
        pessoa.setTelefone("44567880098");
        pessoa.setEmail("teste@teste.com");

        testEntityManager.persist(pessoa);

        String novoNome = "Jean Soares";

        pessoa.setNome(novoNome);

        pessoaRepository.save(pessoa);

        Assertions.assertThat(testEntityManager
                            .find(Pessoa.class, pessoa.getId())
                            .getNome())
                            .isEqualTo(novoNome);
    }

    @Test
    public void findByIdTest() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Ricardo");
        pessoa.setCpf("78945612310");
        pessoa.setTelefone("44567880098");
        pessoa.setEmail("teste@teste.com");

        testEntityManager.persist(pessoa);

        Optional<Pessoa> pessoaRecuperada = pessoaRepository.findById(pessoa.getId());

        Assertions.assertThat(pessoaRecuperada).contains(pessoa);

    }
}

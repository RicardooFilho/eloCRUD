package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/local.sql", "/sql/pessoa.sql"})
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void buscaPessoaPorNomeContainingTest() {
        List<Pessoa> pessoas = pessoaRepository.findByNomeContaining("a");

        assertThat(pessoas).hasSize(4);
    }

    @Test
    public void atualizaPessoaTest() {
        Pessoa pessoa = pessoaRepository.findByCpfContaining("28294");

        pessoa.setCpf("46477850844");

        Pessoa pessoaAtualizada = pessoaRepository.saveAndFlush(pessoa);

        assertThat(pessoaAtualizada.getCpf()).isEqualTo("46477850844");
    }

    /*@Test
    public void UpdateDePessoaTest() {

        Pessoa pessoa = pessoaRepository.findById(1L).get();
        pessoa.setNome("Jean Soares");

        Pessoa pessoaSalvada = pessoaRepository.save(pessoa);

        assertThat(pessoaSalvada.getNome()).isEqualTo("Jean Soares");


    }*/

    /*@Test
    public void findByIdTest() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Ricardo");
        pessoa.setCpf("78945612310");
        pessoa.setTelefone("44567880098");
        pessoa.setEmail("teste@teste.com");

        testEntityManager.persist(pessoa);

        Optional<Pessoa> pessoaRecuperada = pessoaRepository.findById(pessoa.getId());

        assertThat(pessoaRecuperada).contains(pessoa);

    }*/
}

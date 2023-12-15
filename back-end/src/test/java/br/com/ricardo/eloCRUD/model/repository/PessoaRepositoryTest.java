package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import br.com.ricardo.eloCRUD.repository.LocalRepository;
import br.com.ricardo.eloCRUD.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/local.sql", "/sql/pessoa.sql"})
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LocalRepository localRepository;

    @Test
    public void buscaPessoaPorNomeContainingTest() {
        List<Pessoa> pessoas = pessoaRepository.findByNomeContaining("a");

        assertThat(pessoas).extracting(Pessoa::getId)
                .hasSize(4)
                .containsExactlyInAnyOrder(12L, 14L, 13L, 11L);
    }

    @Test
    public void salvaPessoaTest() {
        Pessoa pessoa = new Pessoa();

        List<Local> locaisSalvos = localRepository.findAll();

        pessoa.setNome("Ricardo");
        pessoa.setCpf("46477580949");
        pessoa.setTelefone("44896630542");
        pessoa.setEmail("teste@teste.com");
        pessoa.setLocais(locaisSalvos);

        pessoaRepository.save(pessoa);

        List<Pessoa> pessoasSalvas = pessoaRepository.findAll();

        assertThat(pessoasSalvas).extracting(Pessoa::getId)
                .hasSize(6)
                .containsExactlyInAnyOrder(1L, 10L, 14L, 11L, 13L, 12L);

        assertThat(pessoa.getLocais()).extracting(Local::getId)
                .hasSize(5)
                .containsExactlyInAnyOrder(10L, 12L, 11L, 14L, 13L);
    }

    /*@Test
    public void atualizaPessoaTest() {
        Pessoa pessoa = pessoaRepository.findByCpfContaining("28294");

        pessoa.setCpf("46477850844");

        Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);

        assertThat(pessoaAtualizada.getCpf()).isEqualTo("46477850844");
    }*/

    @Test
    public void deletaPessoaTest() {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Ricardo");
        pessoa.setCpf("46477580949");
        pessoa.setTelefone("44896630542");
        pessoa.setEmail("teste@teste.com");

        pessoaRepository.save(pessoa);
        pessoaRepository.deleteAllById(List.of(2L, 10L));

        List<Pessoa> pessoasSalvas = pessoaRepository.findAll();

        assertThat(pessoasSalvas).extracting(Pessoa::getId)
                .hasSize(4)
                .containsExactlyInAnyOrder(14L, 13L, 12L, 11L);
    }

}

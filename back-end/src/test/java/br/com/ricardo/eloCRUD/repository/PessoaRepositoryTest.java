package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Local;
import br.com.ricardo.eloCRUD.domain.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/sql/pessoa.sql")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

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

        pessoa.setNome("Ricardo");
        pessoa.setCpf("46477580949");
        pessoa.setTelefone("44896630542");
        pessoa.setEmail("teste@teste.com");
        pessoa.setLocais(List.of(new Local()));

        Pessoa novaPessoa = pessoaRepository.save(pessoa);

        assertThat(novaPessoa).isNotNull();
        assertThat(novaPessoa.getId()).isNotNull().isEqualTo(1);
        assertThat(novaPessoa.getNome()).isEqualTo("Ricardo");
        assertThat(novaPessoa.getCpf()).isEqualTo("46477580949");
        assertThat(novaPessoa.getTelefone()).isEqualTo("44896630542");
        assertThat(novaPessoa.getEmail()).isEqualTo("teste@teste.com");
        assertThat(novaPessoa.getLocais()).hasSize(1);

    }

    @Test
    public void atualizaPessoaTest() {
        Pessoa pessoa = pessoaRepository.findById(10L).get();

        pessoa.setCpf("46477850744");

        Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);

        assertThat(pessoaAtualizada.getId()).isNotNull().isEqualTo(10L);
        assertThat(pessoaAtualizada.getNome()).isEqualTo("Mário Pedro");
        assertThat(pessoaAtualizada.getCpf()).isEqualTo("46477850744");
        assertThat(pessoaAtualizada.getTelefone()).isEqualTo("9235240203");
        assertThat(pessoaAtualizada.getEmail()).isEqualTo("daniela@agaxtur.com.br");

    }

    @Test
    public void deletaPessoaTest() {
        pessoaRepository.deleteById(10L);

        List<Pessoa> pessoasSalvas = pessoaRepository.findAll();

        assertThat(pessoasSalvas).extracting(Pessoa::getId)
                .hasSize(4)
                .containsExactlyInAnyOrder(14L, 13L, 12L, 11L);
    }

}*/

package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Local;
import br.com.ricardo.eloCRUD.model.domain.Pessoa;
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
public class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void buscaLocalPorNomeContaining() {
        List<Local> locais = localRepository.findByDescricaoContaining("de");

        assertThat(locais).extracting(Local::getId)
                .hasSize(4)
                .containsExactlyInAnyOrder(9L, 3L, 4L, 7L);
    }

    @Test
    public void salvaLocalTest() { //save
        Local local = new Local();

        local.setDescricao("Gabinete do Prefeito");
        List<Pessoa> pessoas = pessoaRepository.findAll();
        local.setPessoas(pessoas);

        Local novoLocal = localRepository.saveAndFlush(local);

        List<Local> locais = localRepository.findAll();

        assertThat(locais).extracting(Local::getId)
                .hasSize(6)
                .containsExactlyInAnyOrder(9L, 3L, 4L, 7L, 6L, 2L);

        assertThat(novoLocal.getPessoas()).extracting(Pessoa::getId)
                .hasSize(5)
                .containsExactlyInAnyOrder(1L, 5L, 2L, 10L, 7L);
    }

    @Test
    public void atualizaLocalTest() { //update
        Local local = localRepository.findById(4L).get();
        local.setDescricao("Setor de Alimentos");

        Local LocalAtualizado = localRepository.saveAndFlush(local);

        assertThat(LocalAtualizado.getDescricao())
                .isEqualTo("Setor de Alimentos");

    }

    @Test
    public void deletaLocalTest() { //delete
        Local local = new Local();

        local.setDescricao("Setor de Automotivos");

        localRepository.saveAndFlush(local);
        localRepository.deleteById(1L);
        localRepository.deleteById(3L);

        List<Local> locais = localRepository.findAll();

        assertThat(locais).hasSize(4);
    }
}
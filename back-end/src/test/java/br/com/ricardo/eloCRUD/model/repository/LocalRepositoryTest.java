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
                .containsExactlyInAnyOrder(10L, 11L, 13L, 14L);
    }

    @Test
    public void salvaLocalTest() { //save
        Local local = new Local();

        local.setDescricao("Gabinete do Prefeito");
        List<Pessoa> pessoas = pessoaRepository.findAll();
        local.setPessoas(pessoas);

        Local novoLocal = localRepository.save(local);

        assertThat(novoLocal).isNotNull();
        assertThat(novoLocal.getId()).isNotNull();
        assertThat(novoLocal.getDescricao()).isEqualTo("Gabinente do Prefeito");
        assertThat(novoLocal.getPessoas()).extracting(Pessoa::getId)
                .hasSize(5)
                .containsExactlyInAnyOrder(14L, 13L, 11L, 12L, 10L);
    }

    @Test
    public void atualizaLocalTest() { //update
        Local local = localRepository.findById(13L).get();
        local.setDescricao("Setor de Alimentos");

        Local localAtualizado = localRepository.save(local);

        assertThat(localAtualizado.getDescricao())
                .isEqualTo("Setor de Alimentos");
        assertThat(localAtualizado.getId()).isEqualTo(13L);

    }

    @Test
    public void deletaLocalTest() { //delete

        localRepository.deleteById(13L);

        List<Local> locais = localRepository.findAll();

        assertThat(locais).extracting(Local::getId)
                .hasSize(4)
                .containsExactlyInAnyOrder(10L, 14L, 12L, 11L);
    }
}
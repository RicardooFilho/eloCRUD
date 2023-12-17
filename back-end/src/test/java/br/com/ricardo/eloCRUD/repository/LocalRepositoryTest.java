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

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/sql/local.sql")
public class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Test
    public void buscaLocalPorDescricaoContaining() {
        List<Local> locais = localRepository.findByDescricaoContaining("de");

        assertThat(locais).extracting(Local::getId)
                .hasSize(4)
                .containsExactlyInAnyOrder(10L, 11L, 13L, 14L);
    }

    @Test
    public void salvaLocalTest() { //save
        Local local = new Local();

        local.setDescricao("Gabinete do Prefeito");
        local.setPessoas(List.of(new Pessoa()));

        Local novoLocal = localRepository.save(local);

        assertThat(novoLocal).isNotNull();
        assertThat(novoLocal.getId()).isNotNull().isEqualTo(1);
        assertThat(novoLocal.getDescricao()).isEqualTo("Gabinete do Prefeito");
        assertThat(novoLocal.getPessoas()).hasSize(1);
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
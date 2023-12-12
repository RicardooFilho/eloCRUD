package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("dev")
@Sql(scripts = {"/sql/locais.sql"})
public class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Test
    public void buscaLocalPorNomeStartingWith() {
        Local local1 = new Local();
        Local local2 = new Local();
        Local local3 = new Local();

        local1.setDescricao("Setor de Agricultura");

        local2.setDescricao("Setor de Educação");

        local3.setDescricao("Prefeitura Municipal");

        localRepository.save(local1);
        localRepository.save(local2);
        localRepository.save(local3);

        List<Local> locais = localRepository.findByDescricaoStartingWith("Set");

        assertThat(locais).hasSize(2);
        assertThat(locais).extracting(Local::getId).containsExactlyInAnyOrder(1L, 2L);
    }
    //save
    //udpate
    //delete

}
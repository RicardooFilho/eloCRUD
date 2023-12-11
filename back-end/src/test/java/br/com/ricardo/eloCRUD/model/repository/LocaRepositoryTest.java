package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("dev")
public class LocaRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Test
    public void buscaLocalPorNomeStartingWith() {
        Local local1 = new Local();
        Local local2 = new Local();
        Local local3 = new Local();

        local1.setId(1L);
        local1.setDescricao("Setor de Agricultura");

        local2.setId(2L);
        local2.setDescricao("Setor de Educação");

        local3.setId(3L);
        local3.setDescricao("Prefeitura Municipal");

        localRepository.save(local1);
        localRepository.save(local2);
        localRepository.save(local3);

        List<Local> locais = localRepository.findByDescricaoStartingWith("Set");

        Assertions.assertEquals(2, locais.size());

    }
}

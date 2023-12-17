package br.com.ricardo.eloCRUD.repository;

import br.com.ricardo.eloCRUD.domain.Status;
import br.com.ricardo.eloCRUD.enums.SituacaoEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/sql/status.sql")
class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;

    @Test
    public void buscaStatusPorDescricaoContainingTest() {
        List<Status> status = statusRepository.findByDescricaoContaining("a");

        assertThat(status).extracting(Status::getId)
                .hasSize(3)
                .containsExactlyInAnyOrder(10L, 11L, 12L);
    }

    @Test
    public void salvaStatusTest() {
        Status status = new Status();

        status.setDescricao("Atendendo");
        status.setSituacao(SituacaoEnum.PENDENTE);

        Status novoStatus = statusRepository.save(status);

        assertThat(novoStatus).isNotNull();
        assertThat(novoStatus.getId()).isNotNull().isEqualTo(1L);
        assertThat(novoStatus.getDescricao()).isEqualTo("Atendendo");
        assertThat(novoStatus.getSituacao()).isEqualTo(SituacaoEnum.PENDENTE);
    }

    @Test
    public void atualizaStatusTest() {
        Status status = statusRepository.findById(10L).get();

        status.setDescricao("Solicitado exclusão");
        status.setSituacao(SituacaoEnum.CANCELADO);

        Status statusAtualizado = statusRepository.save(status);

        assertThat(statusAtualizado.getId()).isEqualTo(10L);
        assertThat(statusAtualizado.getDescricao()).isEqualTo("Solicitado exclusão");
        assertThat(statusAtualizado.getSituacao()).isEqualTo(SituacaoEnum.CANCELADO);
    }

    @Test
    public void deletaStatusTest() {
        statusRepository.deleteById(10L);

        List<Status> status = statusRepository.findAll();

        assertThat(status).extracting(Status::getId)
                .hasSize(2)
                .containsExactlyInAnyOrder(12L, 11L);
    }

}
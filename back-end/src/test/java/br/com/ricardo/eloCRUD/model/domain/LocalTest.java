package br.com.ricardo.eloCRUD.model.domain;

import br.com.ricardo.eloCRUD.domain.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalTest {

    @Test
    public void criacaoDeLocalTest() {
        Local local = new Local();

        local.setId(1L);
        local.setDescricao("Setor de Agricultura");

        Assertions.assertEquals(1L, local.getId());
        Assertions.assertEquals("Setor de Agricultura", local.getDescricao());
    }
}

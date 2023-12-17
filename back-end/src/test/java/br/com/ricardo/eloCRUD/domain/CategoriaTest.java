package br.com.ricardo.eloCRUD.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CategoriaTest {

    @Test
    public void criacaoDeCategoriaTest() {
        Categoria categoria = new Categoria();

        categoria.setId(1L);
        categoria.setDescricao("Solicitação");

        assertThat(categoria.getId()).isEqualTo(1L);
        assertThat(categoria.getDescricao()).isEqualTo("Solicitação");
    }

}
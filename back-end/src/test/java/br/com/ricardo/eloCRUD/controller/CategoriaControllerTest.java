package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoriaController categoriaController;

    @Test
    @Order(1)
    public void categoriaPostTest() throws Exception {
        Categoria categoria = new Categoria();

        categoria.setId(1L);
        categoria.setDescricao("Na fila");

        mockMvc.perform(post("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void categoriaGetAllTest() throws Exception{
        mockMvc.perform(get("/api/categorias"))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.content[0].descricao").value("Na fila"),
                        jsonPath("$.content[0].id").value(1));
    }

    @Test
    @Order(3)
    public void categoriaGetOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/categorias/{id}", id))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.descricao").value("Na fila"));
                        jsonPath("$.id").value(1);
    }

    @Test
    @Order(4)
    public void categoriaPutTest() throws Exception {
        Long id = 1L;

        String requestBody = "{ \"descricao\": \"Em análise\" }";

        mockMvc.perform(put("/api/categorias/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void categoriaGetDepoisDoPutTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/categorias/{id}", id))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.id").value(1),
                        jsonPath("$.descricao").value("Em análise"));
    }

    @Test
    @Order(6)
    public void categoriaDeleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/categorias/{id}", id))
                .andExpect(status().isNoContent());
    }
}
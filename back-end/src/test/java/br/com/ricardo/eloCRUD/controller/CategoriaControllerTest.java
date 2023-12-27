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

        categoria.setDescricao("Na fila");

        mockMvc.perform(post("/api/categorias")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void categoriaGetAllTest() throws Exception{
        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath( "$[0].descricao").value("xxx"))
                .andExpect(jsonPath( "$[0].descricao").value("xxxxxx"));
    }

    @Test
    @Order(3)
    public void categoriaGetOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/categorias/{id}", id))
                .andExpect(status().isOk());
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
    public void categoriaDeleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/categorias/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    public void categoriaGetDepoisDoDeleteTest() throws Exception {
        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isNoContent());
    }

}
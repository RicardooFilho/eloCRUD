package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Local;
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
class LocalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocalController localController;

    @Test
    @Order(1)
    public void localPostTest() throws Exception {
        Local local = new Local();

        local.setId(1L);
        local.setDescricao("Gabinete do Prefeito");

        mockMvc.perform(post("/api/locais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(local)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void localGetAllTest() throws Exception {
        mockMvc.perform(get("/api/locais"))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.content[0].id").value(1),
                        jsonPath("$.content[0].descricao").value("Gabinete do Prefeito"));
    }

    @Test
    @Order(3)
    public void localGetOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/locais/{id}", id))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1),
                        jsonPath("$.descricao").value("Gabinete do Prefeito"));
    }

    @Test
    @Order(4)
    public void localPutTest() throws Exception {
        Long id = 1L;

        String requestBody = "{ \"descricao\": \"Setor de Agricultura\"}";

        mockMvc.perform(put("/api/locais/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void localGetDepoisDoPutTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/locais/{id}", id))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.id").value(1),
                        jsonPath("$.descricao").value("Setor de Agricultura"));
    }

    @Test
    @Order(6)
    public void localDeleteOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/locais/{id}", id))
                .andExpect(status().isNoContent());
    }


}
package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Local;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LocalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocalController localController;

    @Test
    public void localPostTest() throws Exception {
        Local local = new Local();

        local.setDescricao("Gabinete do Prefeito");

        mockMvc.perform(post("/api/local")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(local)))
                .andExpect(status().isCreated());
    }

    @Test
    public void localGetAllTest() throws Exception {
        mockMvc.perform(get("/api/local"))
                .andExpect(status().isOk());
    }

    @Test
    public void localGetOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/local/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void localPutTest() throws Exception {
        Long id = 1L;

        String requestBody = "{ \"descricao\": \"Setor de Agricultura\" }";

        mockMvc.perform(put("/api/local/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void localDeleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/local/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void localGetDepoisDoDelete() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/local/{id}", id));

        mockMvc.perform(get("/api/local"))
                .andExpect(status().isNoContent());
    }

}
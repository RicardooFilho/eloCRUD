package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Pessoa;
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
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PessoaController pessoaController;

    @Test
    @Order(1)
    public void pessoaPostTest() throws Exception {
        Pessoa pessoa = new Pessoa();

        pessoa.setId(1L);
        pessoa.setNome("Ricardo");
        pessoa.setCpf("46475296025");
        pessoa.setTelefone("44978554126");
        pessoa.setEmail("teste@teste.com");

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void pessoaGetAllTest() throws Exception {
        mockMvc.perform(get("/api/pessoas"))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$[0].id").value(1),
                        jsonPath("$[0].nome").value("Ricardo"),
                        jsonPath("$[0].cpf").value("46475296025"),
                        jsonPath("$[0].telefone").value("44978554126"),
                        jsonPath("$[0].email").value("teste@teste.com"));
    }

    @Test
    @Order(3)
    public void pessoaGetOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/pessoas/{id}", id))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.id").value(1),
                        jsonPath("$.nome").value("Ricardo"),
                        jsonPath("$.cpf").value("46475296025"),
                        jsonPath("$.telefone").value("44978554126"),
                        jsonPath("$.email").value("teste@teste.com"));
    }

    @Test
    @Order(4)
    public void pessoaPutTest() throws Exception {
        Long id = 1L;

        String requestBody = "{ \"nome\": \"Ricardo Filho\"," +
                            "\"cpf\": \"46477582649\"," +
                            "\"telefone\": \"44987556528\"," +
                            "\"email\": \"teste.teste@elotech.com\"}";

        mockMvc.perform(put("/api/pessoas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void pessoaGetDepoisDoPutTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/pessoas/{id}", id))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.id").value(1),
                        jsonPath("$.nome").value("Ricardo Filho"),
                        jsonPath("$.cpf").value("46477582649"),
                        jsonPath("$.telefone").value("44987556528"),
                        jsonPath("$.email").value("teste.teste@elotech.com"));
    }

    @Test
    @Order(6)
    public void pessoaDeleteOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/pessoas/{id}", id))
                .andExpect(status().isNoContent());
    }
}
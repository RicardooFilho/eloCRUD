package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PessoaController pessoaController;

    @Test
    public void pessoaPostTest() throws Exception {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Ricardo");
        pessoa.setCpf("46475296025");
        pessoa.setTelefone("44978554126");
        pessoa.setEmail("teste@teste.com");

        mockMvc.perform(post("/api/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isCreated());
    }

    @Test
    public void pessoaGetAllTest() throws Exception {
        mockMvc.perform(get("/api/pessoa"))
                .andExpect(status().isOk());
    }

    @Test
    public void pessoaGetOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/pessoa/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void pessoaPutOneTest() throws Exception {
        Long id = 1L;

        String requestBody = "{ \"nome\": \"Ricardo Filho\", \"cpf\": \"46477582649\", \"telefone\": \"44987556528\", \"email\": \"teste.teste@elotech.com\"}";

        mockMvc.perform(put("/api/pessoa/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void pessoaDeleteOneTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/pessoa/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void pessoaGetAllDepoisDoDeleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/pessoa/{id}", id));

        mockMvc.perform(get("/api/pessoa"))
                .andExpect(status().isNoContent());
    }

}
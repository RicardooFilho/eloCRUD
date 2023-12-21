package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void case1() throws Exception {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Ricardo");
        pessoa.setCpf("46475296025");
        pessoa.setTelefone("44978554126");
        pessoa.setEmail("teste@teste.com");

        mockMvc.perform(post("/api/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk());
    }


}
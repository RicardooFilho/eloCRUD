package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.domain.*;
import br.com.ricardo.eloCRUD.enums.SituacaoEnum;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TarefaController tarefaController;

    @Test
    @Order(1)
    public void tarefaPostTest() throws Exception {
        Categoria categoria = new Categoria();
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        Local local = new Local();
        Status status = new Status();

        categoria.setId(1L);
        categoria.setDescricao("Na fila");

        pessoa1.setId(1L);
        pessoa1.setNome("Daniel");
        pessoa1.setCpf("12345678910");
        pessoa1.setTelefone("44910112287");
        pessoa1.setEmail("teste@teste.com");

        pessoa2.setId(2L);
        pessoa2.setNome("Ricardo");
        pessoa2.setCpf("12345698710");
        pessoa2.setTelefone("88796332287");
        pessoa2.setEmail("teste@teste.com.br");

        local.setId(2L);
        local.setDescricao("Setor Administrativo");

        status.setId(1L);
        status.setDescricao("Em Espera");
        status.setSituacao(SituacaoEnum.PENDENTE);

        mockMvc.perform(post("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)));

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa1)));

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa2)));

        mockMvc.perform(post("/api/locais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(local)));

        mockMvc.perform(post("/api/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(status)));

        Tarefa tarefa = new Tarefa();

        tarefa.setNumero(1L);
        tarefa.setExercicio(2023);
        tarefa.setRequerente(pessoa1);
        tarefa.setTitulo("Título Foda");
        tarefa.setCategoria(categoria);
        tarefa.setDescricao("Descrição Foda");
        tarefa.setRequerido(pessoa2);
        tarefa.setLocalDestino(local);
        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setStatus(status);


        mockMvc.perform(post("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefa)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void tarefaGetAllTest() throws Exception{
        mockMvc.perform(get("/api/tarefas"))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.content[0].numero").value(1),
                        jsonPath("$.content[0].exercicio").value(2023),
                        jsonPath("$.content[0].requerente.id").value(1),
                        jsonPath("$.content[0].titulo").value("Título Foda"),
                        jsonPath("$.content[0].categoria.id").value(1),
                        jsonPath("$.content[0].descricao").value("Descrição Foda"),
                        jsonPath("$.content[0].requerido.id").value(2),
                        jsonPath("$.content[0].localDestino.id").value(2),
                        jsonPath("$.content[0].dataCriacao").value(LocalDate.now().toString()),
                        jsonPath("$.content[0].status.id").value(1));
    }

    @Test
    @Order(3)
    public void tarefaGetOneTest() throws Exception {
        Long numero = 1L;

        mockMvc.perform(get("/api/tarefas/{numero}", numero))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.numero").value(1),
                        jsonPath("$.exercicio").value(2023),
                        jsonPath("$.requerente.id").value(1),
                        jsonPath("$.titulo").value("Título Foda"),
                        jsonPath("$.categoria.id").value(1),
                        jsonPath("$.descricao").value("Descrição Foda"),
                        jsonPath("$.requerido.id").value(2),
                        jsonPath("$.localDestino.id").value(2),
                        jsonPath("$.dataCriacao").value(LocalDate.now().toString()),
                        jsonPath("$.status.id").value(1));
    }

    @Test
    @Order(4)
    public void tarefaPutTest() throws Exception {
       Long numero = 1L;

        String requestBody = "{" +
                "\"requerente\": { \"id\": \"1\" }," +
                "\"titulo\": \"Título Ruim\"," +
                "\"categoria\": { \"id\": \"1\" }," +
                "\"descricao\": \"Descrição Ruim\"," +
                "\"requerido\": { \"id\": \"2\" }," +
                "\"localDestino\": { \"id\": \"2\" }," +
                "\"status\": { \"id\": \"1\" }" +
                "}";


       mockMvc.perform(put("/api/tarefas/{numero}", numero)
               .contentType(MediaType.APPLICATION_JSON)
               .content(requestBody))
               .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void tarefaGetDepoisDoPutTest() throws Exception {
        Long numero = 1L;

        mockMvc.perform(get("/api/tarefas/{numero}", numero))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.numero").value(1),
                        jsonPath("$.exercicio").value(2023),
                        jsonPath("$.requerente.id").value(1),
                        jsonPath("$.titulo").value("Título Ruim"),
                        jsonPath("$.categoria.id").value(1),
                        jsonPath("$.descricao").value("Descrição Ruim"),
                        jsonPath("$.requerido.id").value(2),
                        jsonPath("$.localDestino.id").value(2),
                        jsonPath("$.dataCriacao").value(LocalDate.now().toString()),
                        jsonPath("$.status.id").value(1));
    }

    @Test
    @Order(6)
    public void tarefaDeleteTest() throws Exception{
        Long numero = 1L;

        mockMvc.perform(delete("/api/tarefas/{numero}", numero))
                .andExpect(status().isNoContent());
    }
}

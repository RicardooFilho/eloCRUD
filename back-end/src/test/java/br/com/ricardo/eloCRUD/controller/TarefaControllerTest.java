package br.com.ricardo.eloCRUD.controller;

import br.com.ricardo.eloCRUD.adapter.TarefaAdapter;
import br.com.ricardo.eloCRUD.domain.*;
import br.com.ricardo.eloCRUD.dto.*;
import br.com.ricardo.eloCRUD.enums.SituacaoEnum;
import br.com.ricardo.eloCRUD.repository.TarefaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
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

    @Autowired
    private TarefaAdapter tarefaAdapter;

    @Mock
    private TarefaRepository tarefaRepository;

    @Test
    @Order(1)
    public void tarefaPostTest() throws Exception {
        CategoriaDTO categoriaDto = new CategoriaDTO();
        PessoaDTO pessoaDto1 = new PessoaDTO();
        PessoaDTO pessoaDto2 = new PessoaDTO();
        LocalDTO localDto = new LocalDTO();
        StatusDTO statusDto = new StatusDTO();

        categoriaDto.setId(1L);
        categoriaDto.setDescricao("Na fila");

        pessoaDto1.setId(1L);
        pessoaDto1.setNome("Daniel");
        pessoaDto1.setCpf("12345678910");
        pessoaDto1.setTelefone("44910112287");
        pessoaDto1.setEmail("teste@teste.com");

        pessoaDto2.setId(2L);
        pessoaDto2.setNome("Ricardo");
        pessoaDto2.setCpf("12345698710");
        pessoaDto2.setTelefone("88796332287");
        pessoaDto2.setEmail("teste@teste.com.br");

        localDto.setId(2L);
        localDto.setDescricao("Setor Administrativo");

        statusDto.setId(1L);
        statusDto.setDescricao("Em Espera");
        statusDto.setSituacao(SituacaoEnum.PENDENTE);

        mockMvc.perform(post("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriaDto)));

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoaDto1)));

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoaDto2)));

        mockMvc.perform(post("/api/locais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localDto)));

        mockMvc.perform(post("/api/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statusDto)));

        TarefaDTO tarefaDto = new TarefaDTO();

        tarefaDto.setNumero(1L);
        tarefaDto.setExercicio(2023);
        tarefaDto.setRequerenteDto(pessoaDto1);
        tarefaDto.setTitulo("Título Foda");
        tarefaDto.setCategoriaDto(categoriaDto);
        tarefaDto.setDescricao("Descrição Foda");
        tarefaDto.setRequeridoDto(pessoaDto2);
        tarefaDto.setLocalDestinoDto(localDto);
        tarefaDto.setDataCriacao(LocalDate.now());
        tarefaDto.setStatusDto(statusDto);


        mockMvc.perform(post("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefaDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void tarefaGetAllTest() throws Exception {

        //List<Tarefa> tarefas = List.of(new Tarefa());
        //when(tarefaRepository.findAll()).thenReturn(tarefas);

        mockMvc.perform(get("/api/tarefas"))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.content[0].numero").value(1),
                        jsonPath("$.content[0].exercicio").value(2023),
                        jsonPath("$.content[0].requerenteDto.id").value(1),
                        jsonPath("$.content[0].titulo").value("Título Foda"),
                        jsonPath("$.content[0].categoriaDto.id").value(1),
                        jsonPath("$.content[0].descricao").value("Descrição Foda"),
                        jsonPath("$.content[0].requeridoDto.id").value(2),
                        jsonPath("$.content[0].localDestinoDto.id").value(2),
                        jsonPath("$.content[0].dataCriacao").value(LocalDate.now().toString()),
                        jsonPath("$.content[0].statusDto.id").value(1));


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
                        jsonPath("$.requerenteDto.id").value(1),
                        jsonPath("$.titulo").value("Título Foda"),
                        jsonPath("$.categoriaDto.id").value(1),
                        jsonPath("$.descricao").value("Descrição Foda"),
                        jsonPath("$.requeridoDto.id").value(2),
                        jsonPath("$.localDestinoDto.id").value(2),
                        jsonPath("$.dataCriacao").value(LocalDate.now().toString()),
                        jsonPath("$.statusDto.id").value(1));
    }

    @Test
    @Order(4)
    public void tarefaPutTest() throws Exception {
       Long numero = 1L;

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
                        jsonPath("$.requerenteDto.id").value(1),
                        jsonPath("$.titulo").value("Título Ruim"),
                        jsonPath("$.categoriaDto.id").value(1),
                        jsonPath("$.descricao").value("Descrição Ruim"),
                        jsonPath("$.requeridoDto.id").value(2),
                        jsonPath("$.localDestinoDto.id").value(2),
                        jsonPath("$.dataCriacao").value(LocalDate.now().toString()),
                        jsonPath("$.statusDto.id").value(1));
    }

    @Test
    @Order(6)
    public void tarefaDeleteTest() throws Exception{
        Long numero = 1L;

        mockMvc.perform(delete("/api/tarefas/{numero}", numero))
                .andExpect(status().isNoContent());
    }
}

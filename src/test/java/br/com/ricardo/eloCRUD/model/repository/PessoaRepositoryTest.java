package br.com.ricardo.eloCRUD.model.repository;

import br.com.ricardo.eloCRUD.model.domain.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testaInsertDePessoa() {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("João Henrique");
        pessoa.setCpf("46485296345");
        pessoa.setTelefone("44974558893");
        pessoa.setEmail("teste@teste.com");

        pessoaRepository.save(pessoa);

        List<Pessoa> pessoas = pessoaRepository.pesquisaPessoaPorNome("Hen");

        Assertions.assertEquals(1, pessoas.size());
    }
}

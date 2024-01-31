package br.com.ricardo.eloCRUD.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException() {
        super("Pessoa não encontrada");
    }
}

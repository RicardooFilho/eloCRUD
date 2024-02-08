package br.com.ricardo.eloCRUD.exceptions;

public class StatusNotFoundException extends RuntimeException{
    public StatusNotFoundException() {
        super("Status não encontrado");
    }
}

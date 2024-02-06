package br.com.ricardo.eloCRUD.exceptions;

public class LocalNotFoundException extends RuntimeException{
    public LocalNotFoundException() {
        super("Local não encontrado");
    }
}

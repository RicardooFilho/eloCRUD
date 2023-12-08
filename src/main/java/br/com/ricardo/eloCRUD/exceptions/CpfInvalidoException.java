package br.com.ricardo.eloCRUD.exceptions;

public class CpfInvalidoException extends Exception{
    public CpfInvalidoException() {
        super("O CPF inserido é inválido");
    }
}

package br.com.ricardo.eloCRUD.exceptions;

public class CpfNullException extends Exception{
    public CpfNullException(){
        super("Informe um CPF");
    }
}

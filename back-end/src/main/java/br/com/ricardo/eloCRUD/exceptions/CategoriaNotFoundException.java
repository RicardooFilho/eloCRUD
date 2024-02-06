package br.com.ricardo.eloCRUD.exceptions;

public class CategoriaNotFoundException extends RuntimeException{
    public CategoriaNotFoundException() {
        super("Categoria não encontrada");
    }
}

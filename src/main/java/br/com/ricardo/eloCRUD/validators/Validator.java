package br.com.ricardo.eloCRUD.validators;

import br.com.ricardo.eloCRUD.exceptions.CpfInvalidoException;
import br.com.ricardo.eloCRUD.exceptions.CpfNullException;

import java.util.Objects;


public class Validator {

    public static void validateCpf(String cpf) throws Exception {
        if (Objects.isNull(cpf)) {
            throw new CpfNullException();
        }

        if (cpf.length() != 11) {
           throw new CpfInvalidoException();
        }
    }
}

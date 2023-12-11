package br.com.ricardo.eloCRUD.formatters;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Formatter {

    public static String formatCpf(String cpf, String mascara) throws ParseException {
        MaskFormatter mask = new MaskFormatter(mascara);
        mask.setValueContainsLiteralCharacters(false);

        return mask.valueToString(cpf);
    }

    public static String unformatCpf(String cpf) {
        return cpf.replaceAll("\\.|\\-", "");
    }
    // teste
}

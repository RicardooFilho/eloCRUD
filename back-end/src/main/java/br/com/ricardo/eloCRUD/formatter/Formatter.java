package br.com.ricardo.eloCRUD.formatter;

import br.com.ricardo.eloCRUD.dto.PessoaDTO;
import org.springframework.data.domain.Page;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Formatter {

    public static String formatString(String string, String mascara) {
        try {
            MaskFormatter mask = new MaskFormatter(mascara);
            mask.setValueContainsLiteralCharacters(false);

            return mask.valueToString(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void formatCpfTelefonePageable(Page<PessoaDTO> pessoaDTOPage) {
        pessoaDTOPage.map(pessoaDTO -> {
            pessoaDTO.setCpf(Formatter.formatString(pessoaDTO.getCpf(), "###.###.###-##"));

            return pessoaDTO;
        });

        pessoaDTOPage.map(pessoaDTO -> {
            pessoaDTO.setTelefone(Formatter.formatString(pessoaDTO.getTelefone(), "(##) #####-####"));

            return pessoaDTO;
        });
    }

    public static void formatCpfTelefone(PessoaDTO pessoaDTO) {
        pessoaDTO.setCpf(Formatter.formatString(pessoaDTO.getCpf(), "###.###.###-##"));
        pessoaDTO.setTelefone(Formatter.formatString(pessoaDTO.getTelefone(), "(##) #####-####"));
    }
}

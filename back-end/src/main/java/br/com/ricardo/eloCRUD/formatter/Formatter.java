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

    public static void formatCpfPageable(Page<PessoaDTO> pessoaDTOPage) {
        pessoaDTOPage.map(pessoaDTO -> {
            pessoaDTO.setCpf(Formatter.formatString(pessoaDTO.getCpf(), "###.###.###-##"));

            return pessoaDTO;
        });
    }

    public static void formatTelefonePageable(Page<PessoaDTO> pessoaDTOPage) {
        pessoaDTOPage.map(pessoaDTO -> {
            pessoaDTO.setTelefone(Formatter.formatString(pessoaDTO.getTelefone(), "(##) #####-####"));

            return pessoaDTO;
        });
    }

    public static void formatCpf(PessoaDTO pessoaDTO) {
        pessoaDTO.setCpf(Formatter.formatString(pessoaDTO.getCpf(), "###.###.###-##"));
    }

    public static void formatTelefone(PessoaDTO pessoaDTO) {
        pessoaDTO.setTelefone(Formatter.formatString(pessoaDTO.getTelefone(), "(##) #####-####"));
    }


}

package br.com.ricardo.eloCRUD.adapter;

import br.com.ricardo.eloCRUD.domain.Endereco;
import br.com.ricardo.eloCRUD.dto.EnderecoDTO;
import org.springframework.stereotype.Service;

@Service
public class EnderecoAdapter implements Adapter<EnderecoDTO, Endereco> {

    @Override
    public Endereco toEntity(EnderecoDTO enderecoDTO) {
        return new Endereco(enderecoDTO.getId(),
                            enderecoDTO.getCep(),
                            enderecoDTO.getLogradouro(),
                            enderecoDTO.getBairro(),
                            enderecoDTO.getComplemento(),
                            enderecoDTO.getCidade(),
                            enderecoDTO.getUf());
    }

    @Override
    public EnderecoDTO toDto(Endereco endereco) {
        return new EnderecoDTO(endereco.getId(),
                                endereco.getCep(),
                                endereco.getLogradouro(),
                                endereco.getBairro(),
                                endereco.getComplemento(),
                                endereco.getCidade(),
                                endereco.getUf());
    }
}

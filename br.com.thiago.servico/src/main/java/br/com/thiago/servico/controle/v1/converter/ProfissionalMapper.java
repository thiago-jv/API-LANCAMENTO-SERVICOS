package br.com.thiago.servico.controle.v1.converter;

import br.com.thiago.servico.controle.v1.dto.request.ProfissionalPostDTO;
import br.com.thiago.servico.controle.v1.dto.response.ProfissionalResponseDTO;
import br.com.thiago.servico.domain.model.Profissional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {

    Profissional toProfissional(ProfissionalPostDTO profissional);
    ProfissionalResponseDTO toProfissionalResponseDTO(Profissional profissional);

}

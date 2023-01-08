package br.com.thiago.servico.controle.v1.converter;

import br.com.thiago.servico.controle.v1.dto.request.ServicoPostDTO;
import br.com.thiago.servico.controle.v1.dto.request.ServicoPutDTO;
import br.com.thiago.servico.controle.v1.dto.response.ServicoResponseDTO;
import br.com.thiago.servico.domain.model.Servico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    Servico toServico(ServicoPostDTO servico);

    Servico toServico(ServicoPutDTO servico);
    ServicoResponseDTO toServicoResponseDTO(Servico servico);

    List<ServicoResponseDTO> toServicoResponseDTOList(List<Servico> servicos);

}

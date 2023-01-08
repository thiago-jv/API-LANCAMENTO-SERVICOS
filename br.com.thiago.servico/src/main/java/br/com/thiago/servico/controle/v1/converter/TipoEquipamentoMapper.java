package br.com.thiago.servico.controle.v1.converter;


import br.com.thiago.servico.controle.v1.dto.request.TipoEquipamentoPostDTO;
import br.com.thiago.servico.controle.v1.dto.response.TipoEquipamentoResponseDTO;
import br.com.thiago.servico.domain.model.TipoEquipamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoEquipamentoMapper {

    TipoEquipamento toTipoEquipamento(TipoEquipamentoPostDTO tipoEquipamento);
    TipoEquipamentoResponseDTO toTipoEquipamentoResponseDTO(TipoEquipamento tipoEquipamento);

}

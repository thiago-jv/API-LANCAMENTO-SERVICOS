package br.com.thiago.servico.controle.v1.converter;


import br.com.thiago.servico.controle.v1.dto.request.EquipamentoPostDTO;
import br.com.thiago.servico.controle.v1.dto.response.EquipamentoResponseDTO;
import br.com.thiago.servico.domain.model.Equipamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {

    Equipamento toEquipamento(EquipamentoPostDTO equipamento);
    EquipamentoResponseDTO toEquipamentoResponseDTO(Equipamento equipamento);

}

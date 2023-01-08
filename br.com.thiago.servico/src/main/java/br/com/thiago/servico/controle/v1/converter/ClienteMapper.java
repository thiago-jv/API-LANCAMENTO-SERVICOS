package br.com.thiago.servico.controle.v1.converter;

import br.com.thiago.servico.controle.v1.dto.request.ClientePostDTO;
import br.com.thiago.servico.controle.v1.dto.response.ClienteResponseDTO;
import br.com.thiago.servico.domain.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClientePostDTO cliente);
    ClienteResponseDTO toClienteResponseDTO(Cliente cliente);

}

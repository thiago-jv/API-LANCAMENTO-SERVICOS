package br.com.thiago.servico.controle.v1.dto.response;

import br.com.thiago.servico.domain.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class ClienteResponseDTO extends Pessoa implements Serializable{

	private Long codigo;
}

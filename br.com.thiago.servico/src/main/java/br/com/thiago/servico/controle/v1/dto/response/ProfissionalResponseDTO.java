package br.com.thiago.servico.controle.v1.dto.response;

import br.com.thiago.servico.controle.v1.dto.PessoaDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class ProfissionalResponseDTO extends PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String idade;

}

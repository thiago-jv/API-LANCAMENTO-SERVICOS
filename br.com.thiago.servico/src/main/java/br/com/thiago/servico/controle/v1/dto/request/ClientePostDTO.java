package br.com.thiago.servico.controle.v1.dto.request;

import br.com.thiago.servico.controle.v1.dto.PessoaDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientePostDTO extends PessoaDTO implements Serializable{

	@ApiModelProperty(example = "33", required = true)
	private static final long serialVersionUID = 1L;

}

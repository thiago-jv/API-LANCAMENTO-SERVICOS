package br.com.thiago.servico.controle.v1.dto.request;

import br.com.thiago.servico.controle.v1.dto.PessoaDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class ProfissionalPostDTO  extends PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "34", required = true)
	private String idade;

}

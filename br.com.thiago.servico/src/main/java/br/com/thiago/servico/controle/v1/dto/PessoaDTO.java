package br.com.thiago.servico.controle.v1.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "Thiago Henrique Melo da Silva", required = true)
	private String nome;
	@ApiModelProperty(example = "thiago.henrique@hotmail.com", required = true)
	private String email;
	@ApiModelProperty(example = "92991679485", required = true)
	private String contato;
	private EnderecoDTO endereco;

}

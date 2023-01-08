package br.com.thiago.servico.controle.v1.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "....")
	private String logradouro;
	@ApiModelProperty(example = "264")
	private String numero;
	@ApiModelProperty(example = "....")
	private String complemento;
	@ApiModelProperty(example = "Monte das Oliveiras")
	private String bairro;
	@ApiModelProperty(example = "69093118")
	private String cep;
	@ApiModelProperty(example = "Manaus")
	private String cidade;
	@ApiModelProperty(example = "AM")
	private String estado;

}

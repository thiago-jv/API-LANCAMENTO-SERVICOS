package br.com.thiago.servico.controle.v1.dto.request;

import br.com.thiago.servico.controle.v1.dto.id.TipoEquipamentoIdDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EquipamentoPostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "periferico", required = true)
	private String descricao;
	private TipoEquipamentoIdDTO tipoEquipamento;

}

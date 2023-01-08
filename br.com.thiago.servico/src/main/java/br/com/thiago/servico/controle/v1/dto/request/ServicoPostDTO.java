package br.com.thiago.servico.controle.v1.dto.request;

import br.com.thiago.servico.controle.v1.dto.StatusServicoDTO;
import br.com.thiago.servico.controle.v1.dto.id.ClienteIdDTO;
import br.com.thiago.servico.controle.v1.dto.id.ProfissionalIdDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ServicoPostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "Menutenção de computador com defeito", required = true)
	private String descricao;

	@ApiModelProperty(example = "ABERTO", required = true)
	private StatusServicoDTO status = StatusServicoDTO.ABERTO;

	@ApiModelProperty(example = "2021-01-16", required = true)
	private LocalDate dtabertura  = LocalDate.now();

	@ApiModelProperty(example = "2021-01-16")
	private LocalDate dtpendencia;

	@ApiModelProperty(example = "2021-01-16")
	private LocalDate dtfechamento;

	@ApiModelProperty(example = "Houve um problema na monutenção do computador")
	private String observacao;

	private ProfissionalIdDTO profissional;

	private ClienteIdDTO cliente;

}

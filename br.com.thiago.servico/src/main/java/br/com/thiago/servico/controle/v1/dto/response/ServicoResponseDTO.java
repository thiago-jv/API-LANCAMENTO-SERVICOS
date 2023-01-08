package br.com.thiago.servico.controle.v1.dto.response;

import br.com.thiago.servico.controle.v1.dto.StatusServicoDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ServicoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String descricao;
	private StatusServicoDTO status = StatusServicoDTO.ABERTO;
	private LocalDate dtabertura  = LocalDate.now();

	private LocalDate dtpendencia;
	private LocalDate dtfechamento;
	private String observacao;
	private ProfissionalResponseDTO profissional;
	private ClienteResponseDTO cliente;

}

package br.com.thiago.servico.controle.v1.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TipoEquipamentoResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigo;
    private String descricao;

}

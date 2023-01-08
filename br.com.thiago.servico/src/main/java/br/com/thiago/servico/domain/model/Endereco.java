package br.com.thiago.servico.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "logradouro", length = 90)
	private String logradouro;

	@Column(name = "numero", length = 10)
	private String numero;

	@Column(name = "complemento", length = 90)
	private String complemento;

	@Column(name = "bairro", length = 90)
	private String bairro;

	@Column(name = "cep", length = 9)
	private String cep;

	@Column(name = "cidade", length = 60)
	private String cidade;

	@Column(name = "estado", length = 60)
	private String estado;

}

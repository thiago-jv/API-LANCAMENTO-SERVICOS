package br.com.thiago.servico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModelProperty;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "....")
	@Column(name = "logradouro", length = 90)
	private String logradouro;

	@ApiModelProperty(example = "264")
	@Column(name = "numero", length = 10)
	private String numero;

	@ApiModelProperty(example = "....")
	@Column(name = "complemento", length = 90)
	private String complemento;

	@ApiModelProperty(example = "Monte das Oliveiras")
	@Column(name = "bairro", length = 90)
	private String bairro;

	@ApiModelProperty(example = "69093118")
	@Column(name = "cep", length = 9)
	private String cep;

	@ApiModelProperty(example = "Manaus")
	@Column(name = "cidade", length = 60)
	private String cidade;

	@ApiModelProperty(example = "Amazonas")
	@Column(name = "estado", length = 60)
	private String estado;

	public Endereco() {
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}

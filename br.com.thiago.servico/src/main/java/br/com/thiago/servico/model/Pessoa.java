package br.com.thiago.servico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "Thiago Henrique Melo da Silva", required = true)
	@NotEmpty(message = "Nome: campo obrigatório")
	@Column(name = "nome", length = 90, nullable = false)
	private String nome;

	@ApiModelProperty(example = "thiago.henrique@hotmail.com", required = true)
	@NotEmpty(message = "Email: campo obrigatório")
	@Column(name = "email", length = 90, nullable = false)
	private String email;

	@ApiModelProperty(example = "92991679485", required = true)
	@NotEmpty(message = "Contato: campo obrigatório")
	@Column(name = "contato", length = 14, nullable = false)
	private String contato;

	@Embedded
	private Endereco endereco;

	public Pessoa() {
	}

	public Pessoa(String nome, String email, String contato, Endereco endereco) {
		this.nome = nome;
		this.email = email;
		this.contato = contato;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		StringBuilder info = new StringBuilder("Nome: ").append(nome)
				                 .append(", Email: ").append(email)
				                 .append(", Constato: ").append(contato);
		return info.toString();
	}

}

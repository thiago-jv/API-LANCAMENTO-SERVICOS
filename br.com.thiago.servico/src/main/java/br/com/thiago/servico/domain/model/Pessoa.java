package br.com.thiago.servico.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@MappedSuperclass
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Nome: campo obrigatório")
	@Column(name = "nome", length = 90, nullable = false)
	private String nome;

	@NotEmpty(message = "Email: campo obrigatório")
	@Column(name = "email", length = 90, nullable = false)
	private String email;

	@NotEmpty(message = "Contato: campo obrigatório")
	@Column(name = "contato", length = 14, nullable = false)
	private String contato;

	@Embedded
	private Endereco endereco;

	@Override
	public String toString() {
		StringBuilder info = new StringBuilder("Nome: ").append(nome)
				                 .append(", Email: ").append(email)
				                 .append(", Constato: ").append(contato);
		return info.toString();
	}

}

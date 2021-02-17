package br.com.thiago.servico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "profissional", schema = "public")
@SequenceGenerator(name = "profissional_seq", sequenceName = "profissional_seq", initialValue = 1, allocationSize = 1)
public class Profissional extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "1", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profissional_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@ApiModelProperty(example = "31")
	@Column(name = "idade", length = 12)
	private String idade;

	public Profissional() {
	}

	public Profissional(String nome, String email, String contato, Endereco endereco, Long codigo, String idade) {
		super(nome, email, contato, endereco);
		this.codigo = codigo;
		this.idade = idade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profissional other = (Profissional) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString()).append(", Codigo: ").append(codigo)
				.append(", Idade: ").append(idade);
		return builder.toString();
	}
}

package br.com.thiago.servico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tipoequipamento", schema = "public")
@SequenceGenerator(name = "tipoequipamento_seq", sequenceName = "tipoequipamento_seq", initialValue = 1, allocationSize = 1)
public class TipoEquipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "1", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoequipamento_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@ApiModelProperty(example = "Hardware", required = true)
	@NotEmpty(message = "Descrição: campo obrigatório")
	@Column(name = "descricao", length = 90, nullable = false)
	private String descricao;

	public TipoEquipamento() {
	}

	public TipoEquipamento(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		TipoEquipamento other = (TipoEquipamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString())
				               .append(", Codigo: ").append(codigo)
				               .append(", Descrição: ").append(descricao);
		return builder.toString();
	}
}

package br.com.thiago.servico.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "equipamento", schema = "public")
@SequenceGenerator(name = "equipamento_seq", sequenceName = "equipamento_seq", initialValue = 1, allocationSize = 1)
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipamento_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@NotEmpty(message = "Descrição: campo obrigatório")
	@Column(name = "descricao", length = 90, nullable = false)
	private String descricao;

	@NotNull(message = "fk_tipoEquipamento: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_tipoEquipamento")
	@JoinColumn(referencedColumnName = "codigo", nullable = false)
	private TipoEquipamento tipoEquipamento;

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
		Equipamento other = (Equipamento) obj;
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
				               .append(", Descrição: ").append(descricao)
				               .append(", Tipo equipamento").append(tipoEquipamento);
		return builder.toString();
	}
}

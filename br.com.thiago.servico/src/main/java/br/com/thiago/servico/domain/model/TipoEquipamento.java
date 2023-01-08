package br.com.thiago.servico.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "tipoequipamento", schema = "public")
@SequenceGenerator(name = "tipoequipamento_seq", sequenceName = "tipoequipamento_seq", initialValue = 1, allocationSize = 1)
public class TipoEquipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoequipamento_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@NotEmpty(message = "Descrição: campo obrigatório")
	@Column(name = "descricao", length = 90, nullable = false)
	private String descricao;

}

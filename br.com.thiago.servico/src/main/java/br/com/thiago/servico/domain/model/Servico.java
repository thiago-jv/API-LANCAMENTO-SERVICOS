package br.com.thiago.servico.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@Entity
@Table(name = "servico", schema = "public")
@SequenceGenerator(name = "servico_seq", sequenceName = "servico_seq", initialValue = 1, allocationSize = 1)
public class Servico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@NotEmpty(message = "Descrição: campo obrigatório")
	@Column(name = "descricao", columnDefinition = "text", nullable = false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10, nullable = false)
	private StatusServico status = StatusServico.ABERTO;

	@NotNull(message = "Dt Início: campo obrigatório")
	@Column(name = "dtabertura", nullable = false)
	private LocalDate dtabertura  = LocalDate.now();

	@Column(name = "dtpendencia")
	private LocalDate dtpendencia;

	@Column(name = "dtfechamento")
	private LocalDate dtfechamento;

	@Column(name = "observacao", length = 255)
	private String observacao;

	@NotNull(message = "fk_profissional: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_profissional")
	@JoinColumn(referencedColumnName = "codigo", nullable = false)
	private Profissional profissional;

	@NotNull(message = "fk_cliente: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_cliente")
	@JoinColumn(referencedColumnName = "codigo", nullable = false)
	private Cliente cliente;

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
		Servico other = (Servico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(", Codigo: ").append(codigo)
				.append(", Descrição Abertura: ").append(descricao)
				.append(", Status: ").append(status)
				.append(", Dt Abertura: ").append(dtabertura)
				.append(", Dt Pendencia: ").append(dtpendencia)
				.append(", Dt Fechamento: ").append(dtfechamento)
				.append(", Observação: ").append(observacao)
				.append(", Cliente: ").append(cliente)
				.append(", Profissional").append(profissional);
		return builder.toString();
	}

	@Transient
	public void pendente() {
		setStatus(StatusServico.PENDENTE);
		setDtpendencia(LocalDate.now());
	}

	@Transient
	public void fechado() {
		setStatus(StatusServico.FECHADO);
		setDtfechamento(LocalDate.now());
	}

}

package br.com.thiago.servico.model;

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

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "servico", schema = "public")
@SequenceGenerator(name = "servico_seq", sequenceName = "servico_seq", initialValue = 1, allocationSize = 1)
public class Servico implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "1", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@ApiModelProperty(example = "Menutenção de computador com defeito", required = true)
	@NotEmpty(message = "Descrição: campo obrigatório")
	@Column(name = "descricao", columnDefinition = "text", nullable = false)
	private String descricao;

	@ApiModelProperty(example = "ABERTO", required = true)
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10, nullable = false)
	private StatusServico status = StatusServico.ABERTO;

	@ApiModelProperty(example = "2021-01-16", required = true)
	@NotNull(message = "Dt Início: campo obrigatório")
	@Column(name = "dtabertura", nullable = false)
	private LocalDate dtabertura  = LocalDate.now();

	@ApiModelProperty(example = "2021-01-16")
	@Column(name = "dtpendencia")
	private LocalDate dtpendencia;

	@ApiModelProperty(example = "2021-01-161")
	@Column(name = "dtfechamento")
	private LocalDate dtfechamento;

	@ApiModelProperty(example = "Houve um problema na monutenção do computador")
	@Column(name = "observacao", length = 255)
	private String observacao;

	@ApiModelProperty(example = "fk_profissional", required = true)
	@NotNull(message = "fk_profissional: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_profissional")
	@JoinColumn(referencedColumnName = "codigo", nullable = false)
	private Profissional profissional;

	@ApiModelProperty(example = "fk_cliente", required = true)
	@NotNull(message = "fk_cliente: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_cliente")
	@JoinColumn(referencedColumnName = "codigo", nullable = false)
	private Cliente cliente;

	public Servico() {
	}

	public Servico(Long codigo, String descricao, StatusServico status, LocalDate dtabertura, LocalDate dtpendencia,
			LocalDate dtfechamento, String observacao, Profissional profissional, Cliente cliente) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.status = status;
		this.dtabertura = dtabertura;
		this.dtpendencia = dtpendencia;
		this.dtfechamento = dtfechamento;
		this.observacao = observacao;
		this.profissional = profissional;
		this.cliente = cliente;
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

	public StatusServico getStatus() {
		return status;
	}

	public void setStatus(StatusServico status) {
		this.status = status;
	}

	public LocalDate getDtabertura() {
		return dtabertura;
	}

	public void setDtabertura(LocalDate dtabertura) {
		this.dtabertura = dtabertura;
	}

	public LocalDate getDtpendencia() {
		return dtpendencia;
	}

	protected void setDtpendencia(LocalDate dtpendencia) {
		this.dtpendencia = dtpendencia;
	}

	public LocalDate getDtfechamento() {
		return dtfechamento;
	}

	protected void setDtfechamento(LocalDate dtfechamento) {
		this.dtfechamento = dtfechamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

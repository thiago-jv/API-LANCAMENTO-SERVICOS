package br.com.thiago.servico.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

// utilizando a Especificação RFC 7807 - Problens detains APIs

@JsonInclude(Include.NON_NULL)
public class Problema {

	@ApiModelProperty(example = "400")
	private Integer status;
	
	@ApiModelProperty(example = "https://br.com.projuris//mensagem-incompreensivel")
	private String type;
	
	@ApiModelProperty(example = "Mensagem incompreensível")
	private String title;
	
	@ApiModelProperty(example = "O corpo da requisição está inválido. Verifique erro de sintaxe.")
	private String detail;

	public Problema() {
	}

	public Problema(Integer status, String type, String title, String detail) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
	}

	public Problema(String title, Integer status) {
		this.title = title;
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}
}

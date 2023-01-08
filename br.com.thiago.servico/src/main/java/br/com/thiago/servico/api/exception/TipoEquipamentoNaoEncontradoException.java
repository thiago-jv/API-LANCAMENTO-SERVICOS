package br.com.thiago.servico.api.exception;

public class TipoEquipamentoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public TipoEquipamentoNaoEncontradoException(String messagem) {
		super(messagem);
	}
	
	public TipoEquipamentoNaoEncontradoException(Long codigo) {
		this(String.format("Não existe um cadastro de Tipo Equipamento com o código %d", codigo));
	}
}

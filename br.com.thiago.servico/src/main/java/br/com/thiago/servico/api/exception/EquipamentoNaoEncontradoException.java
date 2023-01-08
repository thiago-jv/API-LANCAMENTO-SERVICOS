package br.com.thiago.servico.api.exception;

public class EquipamentoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public EquipamentoNaoEncontradoException(String messagem) {
		super(messagem);
	}
	
	public EquipamentoNaoEncontradoException(Long codigo) {
		this(String.format("Não existe um cadastro de Equipamento com o código %d", codigo));
	}
}

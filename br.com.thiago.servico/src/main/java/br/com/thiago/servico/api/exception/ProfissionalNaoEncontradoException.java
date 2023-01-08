package br.com.thiago.servico.api.exception;

public class ProfissionalNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProfissionalNaoEncontradoException(String messagem) {
		super(messagem);
	}
	
	public ProfissionalNaoEncontradoException(Long codigo) {
		this(String.format("Não existe um cadastro de Profisisonal com o código %d", codigo));
	}
}

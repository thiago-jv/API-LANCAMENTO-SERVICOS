package br.com.thiago.servico.exception;

public class ServicoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ServicoNaoEncontradoException(String messagem) {
		super(messagem);
	}
	
	public ServicoNaoEncontradoException(Long codigo) {
		this(String.format("Não existe um cadastro de Servico com o código %d", codigo));
	}
}

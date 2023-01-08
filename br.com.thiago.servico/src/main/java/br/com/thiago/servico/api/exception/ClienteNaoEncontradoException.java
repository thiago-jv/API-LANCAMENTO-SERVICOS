package br.com.thiago.servico.api.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String messagem) {
		super(messagem);
	}
	
	public ClienteNaoEncontradoException(Long codigo) {
		this(String.format("Não existe um cadastro de Cliente com o código %d", codigo));
	}
}

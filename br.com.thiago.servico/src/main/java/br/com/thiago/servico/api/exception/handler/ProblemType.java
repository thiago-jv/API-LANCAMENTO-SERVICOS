package br.com.thiago.servico.api.exception.handler;

public enum ProblemType {

	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");
	
	private String title;
	private String uri;

	ProblemType(String path, String title) {
		this.uri = "https://br.com.projuris/" + path;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}

}

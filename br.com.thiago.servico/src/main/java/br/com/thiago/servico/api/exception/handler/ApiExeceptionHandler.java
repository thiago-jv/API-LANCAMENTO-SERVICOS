package br.com.thiago.servico.api.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.thiago.servico.api.exception.EntidadeEmUsoException;
import br.com.thiago.servico.api.exception.EntidadeNaoEncontradaException;
import br.com.thiago.servico.api.exception.NegocioException;


@ControllerAdvice
public class ApiExeceptionHandler extends ResponseEntityExceptionHandler {
	
	public static final String MSG_ERRO
	= "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String detail = MSG_ERRO;

		// Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
		// fazendo logging) para mostrar a stacktrace no console
		// Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
		// para você durante, especialmente na fase de desenvolvimento
		ex.printStackTrace();
		
		Problema problem = createProblemBuilder(status, problemType, detail);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", 
				ex.getRequestURL());
		
		Problema problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	    // 1. MethodArgumentTypeMismatchException é um subtipo de TypeMismatchException

		// 2. ResponseEntityExceptionHandler já trata TypeMismatchException de forma mais abrangente
		
		// 3. Então, especializamos o método handleTypeMismatch e verificamos se a exception
		//    é uma instância de MethodArgumentTypeMismatchException
		
		// 4. Se for, chamamos um método especialista em tratar esse tipo de exception
		
		// 5. Poderíamos fazer tudo dentro de handleTypeMismatch, mas preferi separar em outro método
		@Override
		protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
				HttpStatus status, WebRequest request) {
			
			if (ex instanceof MethodArgumentTypeMismatchException) {
				return handleMethodArgumentTypeMismatch(
						(MethodArgumentTypeMismatchException) ex, headers, status, request);
			}
		
			return super.handleTypeMismatch(ex, headers, status, request);
		}
		
		private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
				MethodArgumentTypeMismatchException ex, HttpHeaders headers,
				HttpStatus status, WebRequest request) {

			ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

			String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
					+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
					ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

			Problema problem = createProblemBuilder(status, problemType, detail);

			return handleExceptionInternal(ex, problem, headers, status, request);
		}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request); 
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		
		Problema problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		// Criei o método joinPath para reaproveitar em todos os métodos que precisam
		// concatenar os nomes das propriedades (separando por ".")
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' não existe. "
				+ "Corrija ou remova essa propriedade e tente novamente.", path);
		
		Problema problem = createProblemBuilder(status, problemType, detail);

		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String path = ex.getPath().stream()
				.map(Reference::getFieldName)
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problema problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {
		

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		
		Problema problema = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
		
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problema problema = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
		Problema problema = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	// customizando o hanldeExceptionInternal
	// pois todos o outros hanldes, utilizando o hanldeExceptionInternal, dessa
	// forma esse métodod é um ponto central
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			// se o corpo for nulo vem a mensagem do status
			Problema problema = new Problema(status.getReasonPhrase(), status.value());
			body = problema;
		} else if (body instanceof String) {
			// caso contrario, vem a String do problema
			Problema problema = new Problema((String) body, status.value());
			body = problema;
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problema createProblemBuilder(HttpStatus status,
			ProblemType problemType, String detail) {
		Problema problema = new Problema(status.value()
				,problemType.getUri()
				,problemType.getTitle()
				,detail);
		
		return problema;	 
	}
	
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(Reference::getFieldName)
			.collect(Collectors.joining("."));
	}
}

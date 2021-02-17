package br.com.thiago.servico.controle;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.servico.exception.handler.Problema;
import br.com.thiago.servico.model.Servico;
import br.com.thiago.servico.model.StatusServico;
import br.com.thiago.servico.repository.ServicoRepository;
import br.com.thiago.servico.service.ServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Servico")
@Controller
@RestController
@RequestMapping(value = "/servicos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServicoControle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private ServicoRepository servicoRepository;

	@ApiOperation("Busca todas as 'Ordem de Serviço'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@GetMapping
	public List<Servico> pesquisar() {
		return servicoRepository.findAll();
	}

	@ApiOperation("Busca por status da 'Ordem de Serviço' e nome do profissional que realizou a criação")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@GetMapping("/status/{status}/profissional/{nome}")
	public List<Servico> listaPorQueryDinamica(@ApiParam(name = "status", value = "Status da 'Ordem de Serviço'")
			@PathVariable StatusServico status, @ApiParam(name = "nome", value = "Nome  do profissional na 'Ordem de Serviço'") @PathVariable String nome) {
		List<Servico> retorno = servicoRepository.buscaServicoProfissionalStatus(nome, status);
		return retorno;
	}

	@ApiOperation("Atualiza a 'Ordem de Serviço'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@PutMapping("/{codigo}")
	public ResponseEntity<Servico> atualizar(@ApiParam(name = "codigo", value = "Código da 'Ordem de Serviço'") @PathVariable Long codigo, @Valid @RequestBody Servico servico) {
		Servico servicoSalvo = servicoService.salvar(codigo, servico);
		return ResponseEntity.ok(servicoSalvo);
	}

	@ApiOperation("Cria 'Ordem de Serviço'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@PostMapping
	public ResponseEntity<Servico> salvar(@ApiParam(name = "corpo", value = "Representação de uma nova 'Ordem de Serviço'") @Valid @RequestBody Servico servico, HttpServletResponse response) {
		Servico servicoSalvo = servicoService.salvar(servico);
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);
	}

	@ApiOperation("Busca por status da 'Ordem de Serviço' e atualiza para 'PENDENTE'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
    })
	@PutMapping("/{codigo}/pendente/{codigoProfissional}/profissional")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void pendente(@ApiParam(name = "codigo", value = "Código da 'Ordem de Serviço'") @PathVariable Long codigo, @ApiParam(name = "codigoProfissional", value = "Código do profissional da 'Ordem de Serviço'") @PathVariable Long codigoProfissional) {
		servicoService.pendente(codigo, codigoProfissional);
	}

	@ApiOperation("Busca por status da 'Ordem de Servico' e atualiza para 'FECHADO'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
	})
	@PutMapping("/{codigo}/fechamento/{codigoProfissional}/profissional")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@ApiParam(name = "codigo", value = "Código da 'Ordem de Serviço'") @PathVariable Long codigo, @ApiParam(name = "codigoProfissional", value = "Código do profissional da 'Ordem de Serviço'") @PathVariable Long codigoProfissional) {
		servicoService.fechado(codigo, codigoProfissional);

	}

}

package br.com.thiago.servico.controle.v1;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.thiago.servico.controle.v1.converter.ServicoMapper;
import br.com.thiago.servico.controle.v1.dto.request.ServicoPostDTO;
import br.com.thiago.servico.controle.v1.dto.request.ServicoPutDTO;
import br.com.thiago.servico.controle.v1.dto.response.ServicoResponseDTO;
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

import br.com.thiago.servico.api.exception.handler.Problema;
import br.com.thiago.servico.domain.model.StatusServico;
import br.com.thiago.servico.domain.repository.ServicoRepository;
import br.com.thiago.servico.domain.service.ServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Servico")
@Controller
@RestController
@RequestMapping(value = "/v1/servicos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServicoControle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ServicoMapper servicoMapper;

	@ApiOperation("Busca todas as 'Ordem de Serviço'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@GetMapping
	public List<ServicoResponseDTO> pesquisar() {
		return servicoMapper.toServicoResponseDTOList(servicoRepository.findAll());
	}

	@ApiOperation("Busca por status da 'Ordem de Serviço' e nome do profissional que realizou a criação")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@GetMapping("/status/{status}/profissional/{nome}")
	public List<ServicoResponseDTO> listaPorQueryDinamica(@ApiParam(name = "status", value = "Status da 'Ordem de Serviço'")
			@PathVariable StatusServico status, @ApiParam(name = "nome", value = "Nome  do profissional na 'Ordem de Serviço'") @PathVariable String nome) {
		return servicoMapper.toServicoResponseDTOList(servicoRepository.buscaServicoProfissionalStatus(nome, status));
	}

	@ApiOperation("Atualiza a 'Ordem de Serviço'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@PutMapping("/{codigo}")
	public ResponseEntity<ServicoResponseDTO> atualizar(@ApiParam(name = "codigo", value = "Código da 'Ordem de Serviço'") @PathVariable Long codigo, @Valid @RequestBody ServicoPutDTO servico) {
		return ResponseEntity.ok(servicoMapper.toServicoResponseDTO(servicoService.salvar(codigo, servicoMapper.toServico(servico))));
	}

	@ApiOperation("Cria 'Ordem de Serviço'")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@PostMapping
	public ResponseEntity<ServicoResponseDTO> salvar(@ApiParam(name = "corpo", value = "Representação de uma nova 'Ordem de Serviço'") @Valid @RequestBody ServicoPostDTO servico, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoMapper.toServicoResponseDTO(servicoService.salvar(servicoMapper.toServico(servico))));
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

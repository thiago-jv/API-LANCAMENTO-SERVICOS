package br.com.thiago.servico.controle.v1;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.thiago.servico.controle.v1.converter.EquipamentoMapper;
import br.com.thiago.servico.controle.v1.dto.request.EquipamentoPostDTO;
import br.com.thiago.servico.controle.v1.dto.response.EquipamentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.servico.api.exception.handler.Problema;
import br.com.thiago.servico.domain.service.EquipamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Equipamento")
@Controller
@RestController
@RequestMapping(value = "/v1/equipamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipamentoControle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EquipamentoService equipamentoService;

	@Autowired
	private EquipamentoMapper mapper;
	
	@ApiOperation("Cria equipamentos")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@PostMapping
	public ResponseEntity<EquipamentoResponseDTO> criar(@ApiParam(name = "corpo", value = "Representação de um novo 'Equipamento'") @Valid @RequestBody EquipamentoPostDTO equipamento,
														HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toEquipamentoResponseDTO(equipamentoService.salvar(mapper.toEquipamento(equipamento))));
	}
}

package br.com.thiago.servico.controle;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import br.com.thiago.servico.exception.handler.Problema;
import br.com.thiago.servico.model.Equipamento;
import br.com.thiago.servico.service.EquipamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Equipamento")
@Controller
@RestController
@RequestMapping(value = "/equipamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipamentoControle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EquipamentoService equipamentoService;
	
	@ApiOperation("Cria equipamentos")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@PostMapping
	public ResponseEntity<Equipamento> criar(@ApiParam(name = "corpo", value = "Representação de um novo 'Equipamento'") @Valid @RequestBody Equipamento equipamento,
			HttpServletResponse response) {
		
		Equipamento equipamentoNovo = equipamentoService.salvar(equipamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoNovo);
	}
}

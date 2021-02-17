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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.servico.exception.handler.Problema;
import br.com.thiago.servico.model.Cliente;
import br.com.thiago.servico.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Cliente")
@Controller
@RestController
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteControle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation("Cria clientes")
	@ApiResponses({
		@ApiResponse(code = 406, message = "O corpo da requisição está inválido. Verifique erro de sintaxe", response = Problema.class),
		@ApiResponse(code = 415, message = "Requisição recusada porque o corpo está em um formato não suportado", response = Problema.class)
	})
	@PostMapping
	public ResponseEntity<Cliente> criar(@ApiParam(name = "corpo", value = "Representação de um novo 'Cliente'")@Valid @RequestBody Cliente cliente, HttpServletResponse response){
		Cliente c = clienteService.salvar(cliente);
	 return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}

}

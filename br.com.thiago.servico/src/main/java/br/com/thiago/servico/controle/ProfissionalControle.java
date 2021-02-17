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

import br.com.thiago.servico.model.Profissional;
import br.com.thiago.servico.service.ProfissionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Profissional")
@Controller
@RestController
@RequestMapping(value = "/profissionais", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfissionalControle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProfissionalService profissionalService;

	@ApiOperation("Cria profissionais")
	@PostMapping
	public ResponseEntity<Profissional> criar(@ApiParam(name = "corpo", value = "Representação de um novo 'Profissional'")@Valid @RequestBody Profissional profissional,
			HttpServletResponse response) {
		Profissional p = profissionalService.salvar(profissional);
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	}
}

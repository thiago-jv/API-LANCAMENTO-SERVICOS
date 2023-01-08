package br.com.thiago.servico.controle.v1;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.thiago.servico.controle.v1.converter.ProfissionalMapper;
import br.com.thiago.servico.controle.v1.dto.request.ProfissionalPostDTO;
import br.com.thiago.servico.controle.v1.dto.response.ProfissionalResponseDTO;
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

import br.com.thiago.servico.domain.service.ProfissionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Profissional")
@Controller
@RestController
@RequestMapping(value = "/v1/profissionais", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfissionalControle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ProfissionalMapper profissionalMapper;

	@ApiOperation("Cria profissionais")
	@PostMapping
	public ResponseEntity<ProfissionalResponseDTO> criar(@ApiParam(name = "corpo", value = "Representação de um novo 'Profissional'")@Valid @RequestBody ProfissionalPostDTO profissional,
														 HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.CREATED).body(profissionalMapper.toProfissionalResponseDTO(profissionalService.salvar(profissionalMapper.toProfissional(profissional))));
	}
}

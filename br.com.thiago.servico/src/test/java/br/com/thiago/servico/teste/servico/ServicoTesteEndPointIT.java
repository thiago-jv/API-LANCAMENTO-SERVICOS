package br.com.thiago.servico.teste.servico;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import br.com.thiago.servico.model.Cliente;
import br.com.thiago.servico.model.Profissional;
import br.com.thiago.servico.model.Servico;
import br.com.thiago.servico.model.StatusServico;
import br.com.thiago.servico.repository.ServicoRepository;
import br.com.thiago.servico.service.ClienteService;
import br.com.thiago.servico.service.ProfissionalService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServicoTesteEndPointIT {
	
	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ServicoRepository servicoRepository;

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/servicos";
	}
	
	@Test
	public void deve_retornar_201_Ao_Salvar(){
		Servico servico = new Servico();

		servico.setDescricao("Manutenção na cadeira");

		Profissional profissional = profissionalService.buscarOuFalhar(1L);
		servico.setProfissional(profissional);
		
		Cliente cliente = clienteService.buscarOuFalhar(1L);
		servico.setCliente(cliente);
		
		RestAssured.given()
		 .body(servico)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
	.when()
		 .post()
	.then()
		 .statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deve_retornar_201_Ao_Atualizar(){
		Servico servico = servicoRepository.findOne(1l);
		
		servico.setDescricao("Manutenção na cadeira , logo será mais caro o serviço");
		servico.setObservacao("Cadeira muito danificada!");
	
		Profissional profissional = profissionalService.buscarOuFalhar(1L);
		servico.setProfissional(profissional);
		
		RestAssured.given()
		 .pathParam("codigo", servico.getCodigo())
		 .body(servico)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
	.when()
		 .put("/{codigo}")
	.then()
		 .statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deve_retornar_201_Ao_Atualizar_Por_Status_Pendente(){
		RestAssured.given()
		 .pathParam("codigo", 1L)
		 .pathParam("codigoProfissional", 1L)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
	.when()
		 .put("/{codigo}/pendente/{codigoProfissional}/profissional")
	.then()
		 .statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void deve_retornar_201_Ao_Atualizar_Por_Status_Fechado(){
		RestAssured.given()
		 .pathParam("codigo", 1L)
		 .pathParam("codigoProfissional", 1L)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
	.when()
		 .put("/{codigo}/fechamento/{codigoProfissional}/profissional")
	.then()
		 .statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void listaPorStatusProfissional() {
		RestAssured.given()
		.pathParam("nome", "Jessica Henrique Araujo da Castro") 
		.pathParam("status", StatusServico.ABERTO)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
	.when()
		 .get("/status/{status}/profissional/{nome}")
	.then()
		 .statusCode(HttpStatus.OK.value());
	}
}

package br.com.thiago.servico.teste.cliente;

import br.com.thiago.servico.controle.v1.dto.EnderecoDTO;
import br.com.thiago.servico.controle.v1.dto.request.ClientePostDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteTesteEndPointIT {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/servicoapi/v1/clientes";
	}
	
    @Test
	public void deve_retornar_2001_Ao_Salvar() {
		var cliente = new ClientePostDTO();
	
		cliente.setNome("Joao Henrique Araujo da Silva");
		cliente.setContato("92991223311");
		cliente.setEmail("joao.henrique.araujo.silva@hotmail.com");
		
		var endereco = new EnderecoDTO();
		endereco.setBairro("Monte das oliveiras");
		endereco.setCep("69093118");
		endereco.setNumero("674");
		endereco.setComplemento("Aguas do Amzonas");
		endereco.setCidade("Manaus");
		endereco.setEstado("Amazonas");
		endereco.setLogradouro("Proximo Aguas do Amazonas");
		cliente.setEndereco(endereco);
		
		RestAssured.given()
		 .body(cliente)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		.when()
		 .post()
		.then()
		 .statusCode(HttpStatus.CREATED.value());
	}
	
}

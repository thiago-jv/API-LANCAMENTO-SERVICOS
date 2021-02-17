package br.com.thiago.servico.teste.cliente;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import br.com.thiago.servico.model.Cliente;
import br.com.thiago.servico.model.Endereco;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteTesteEndPointIT {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes";
	}
	
    @Test
	public void deve_retornar_2001_Ao_Salvar() {
		Cliente cliente = new Cliente();
	
		cliente.setNome("Joao Henrique Araujo da Silva");
		cliente.setContato("92991223311");
		cliente.setEmail("joao.henrique.araujo.silva@hotmail.com");
		
		Endereco endereco = new Endereco();
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

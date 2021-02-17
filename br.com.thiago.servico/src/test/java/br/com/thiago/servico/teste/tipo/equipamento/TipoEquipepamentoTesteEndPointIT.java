package br.com.thiago.servico.teste.tipo.equipamento;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import br.com.thiago.servico.model.TipoEquipamento;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TipoEquipepamentoTesteEndPointIT {

	@LocalServerPort
	private int port = 8080;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/tipos";
	}
	
	@Test
	public void deve_retornar_201_Ao_Salvar(){
		TipoEquipamento tipoEquipamento = new TipoEquipamento();
		tipoEquipamento.setDescricao("Pesado");
		
		RestAssured.given()
		 .body(tipoEquipamento)
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
	.when()
		 .post()
	.then()
		 .statusCode(HttpStatus.CREATED.value());
	}
}

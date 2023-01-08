package br.com.thiago.servico.teste.tipo.equipamento;

import br.com.thiago.servico.controle.v1.dto.request.TipoEquipamentoPostDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TipoEquipepamentoTesteEndPointIT {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/servicoapi/v1/tipos";
	}
	
	@Test
	public void deve_retornar_201_Ao_Salvar(){
		TipoEquipamentoPostDTO tipoEquipamento = new TipoEquipamentoPostDTO();
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

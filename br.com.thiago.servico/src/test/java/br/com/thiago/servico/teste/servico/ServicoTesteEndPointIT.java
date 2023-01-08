package br.com.thiago.servico.teste.servico;

import br.com.thiago.servico.controle.v1.converter.ClienteMapper;
import br.com.thiago.servico.controle.v1.converter.ProfissionalMapper;
import br.com.thiago.servico.controle.v1.dto.EnderecoDTO;
import br.com.thiago.servico.controle.v1.dto.id.ClienteIdDTO;
import br.com.thiago.servico.controle.v1.dto.id.ProfissionalIdDTO;
import br.com.thiago.servico.controle.v1.dto.request.ClientePostDTO;
import br.com.thiago.servico.controle.v1.dto.request.ProfissionalPostDTO;
import br.com.thiago.servico.controle.v1.dto.request.ServicoPostDTO;
import br.com.thiago.servico.controle.v1.dto.request.ServicoPutDTO;
import br.com.thiago.servico.domain.service.ServicoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import br.com.thiago.servico.domain.model.StatusServico;
import br.com.thiago.servico.domain.service.ClienteService;
import br.com.thiago.servico.domain.service.ProfissionalService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServicoTesteEndPointIT {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProfissionalMapper profissionalMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/servicoapi/v1/servicos";
    }

    @Test
    public void deve_retornar_201_Ao_Salvar() {
        var profissional = new ProfissionalPostDTO();
        profissional.setNome("Jessica Henrique Araujo da Castro");
        profissional.setContato("9");
        profissional.setEmail("jessica.henrique.araujo.castro@hotmail.com");
        profissional.setIdade("1989-01-01");
        var enderecoProfissional = new EnderecoDTO();
        enderecoProfissional.setBairro("Monte das oliveiras");
        enderecoProfissional.setCep("69093118");
        enderecoProfissional.setNumero("674");
        enderecoProfissional.setComplemento("Aguas");
        enderecoProfissional.setCidade("Manaus");
        enderecoProfissional.setEstado("Amazonas");
        enderecoProfissional.setLogradouro("Proximo Aguas do Amazonas");
        profissional.setEndereco(enderecoProfissional);
        var profissionalSalvo = profissionalService.salvar(profissionalMapper.toProfissional(profissional));

        var profissionalIdDTO = new ProfissionalIdDTO();
        profissionalIdDTO.setCodigo(profissionalSalvo.getCodigo());


        var cliente = new ClientePostDTO();
        cliente.setNome("Joao Henrique Araujo da Silva");
        cliente.setContato("92991223311");
        cliente.setEmail("joao.henrique.araujo.silva@hotmail.com");
        var enderecoCliente = new EnderecoDTO();
        enderecoCliente.setBairro("Monte das oliveiras");
        enderecoCliente.setCep("69093118");
        enderecoCliente.setNumero("674");
        enderecoCliente.setComplemento("Aguas do Amzonas");
        enderecoCliente.setCidade("Manaus");
        enderecoCliente.setEstado("Amazonas");
        enderecoCliente.setLogradouro("Proximo Aguas do Amazonas");
        cliente.setEndereco(enderecoCliente);
        var clienteSalvo = clienteService.salvar(clienteMapper.toCliente(cliente));

        var clienteIdDTO = new ClienteIdDTO();
        clienteIdDTO.setCodigo(clienteSalvo.getCodigo());


        var servico = new ServicoPostDTO();
        servico.setDescricao("Manutenção na cadeira");
        servico.setProfissional(profissionalIdDTO);
        servico.setCliente(clienteIdDTO);

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
    public void deve_retornar_201_Ao_Atualizar() {
        var servico = new ServicoPutDTO();
        servico.setCodigo(1l);

        servico.setDescricao("Manutenção na cadeira , logo será mais caro o serviço");
        servico.setObservacao("Cadeira muito danificada!");

        var profId = new ProfissionalIdDTO();
        profId.setCodigo(1l);

        var clieId = new ClienteIdDTO();
        clieId.setCodigo(1l);

        servico.setCliente(clieId);
        servico.setProfissional(profId);

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
    public void deve_retornar_201_Ao_Atualizar_Por_Status_Pendente() {
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
    public void deve_retornar_201_Ao_Atualizar_Por_Status_Fechado() {
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

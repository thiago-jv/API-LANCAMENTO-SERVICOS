package br.com.thiago.servico.teste.cliente;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.servico.controle.v1.converter.ClienteMapper;
import br.com.thiago.servico.controle.v1.dto.EnderecoDTO;
import br.com.thiago.servico.controle.v1.dto.request.ClientePostDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.servico.domain.model.Cliente;
import br.com.thiago.servico.domain.model.Endereco;
import br.com.thiago.servico.domain.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTesteIntegracaoIT {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteMapper clienteMapper;
	
	@Test
	public void salvar() {
		var cliente = new ClientePostDTO();
		
		cliente.setNome("Mateus Henrique Araujo da Silva");
		cliente.setContato("92991223341");
		cliente.setEmail("mateus.henrique.araujo.silva@hotmail.com");
		
		var endereco = new EnderecoDTO();
		endereco.setBairro("Monte das oliveiras");
		endereco.setCep("69093118");
		endereco.setNumero("674");
		endereco.setComplemento("Aguas do Amzonas");
		endereco.setCidade("Manaus");
		endereco.setEstado("Amazonas");
		endereco.setLogradouro("Proximo Aguas do Amazonas");
		cliente.setEndereco(endereco);
		
		List<ClientePostDTO> lista = new ArrayList<>();
		lista.add(cliente);
		
		for (ClientePostDTO cli : lista) {
			clienteService.salvar(clienteMapper.toCliente(cli));
		}	
	}
}

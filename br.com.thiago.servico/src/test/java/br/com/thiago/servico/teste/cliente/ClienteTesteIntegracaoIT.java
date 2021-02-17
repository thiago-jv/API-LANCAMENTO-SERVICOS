package br.com.thiago.servico.teste.cliente;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.servico.model.Cliente;
import br.com.thiago.servico.model.Endereco;
import br.com.thiago.servico.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTesteIntegracaoIT {

	@Autowired
	private ClienteService clienteService;
	
	@Test
	public void salvar() {
		Cliente cliente1 = new Cliente();
		
		cliente1.setNome("Mateus Henrique Araujo da Silva");
		cliente1.setContato("92991223341");
		cliente1.setEmail("mateus.henrique.araujo.silva@hotmail.com");
		
		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Monte das oliveiras");
		endereco1.setCep("69093118");
		endereco1.setNumero("674");
		endereco1.setComplemento("Aguas do Amzonas");
		endereco1.setCidade("Manaus");
		endereco1.setEstado("Amazonas");
		endereco1.setLogradouro("Proximo Aguas do Amazonas");
		cliente1.setEndereco(endereco1);
		
		List<Cliente> lista = new ArrayList<>();
		lista.add(cliente1);
		
		for (Cliente cliente : lista) {
			clienteService.salvar(cliente);
		}	
	}
}

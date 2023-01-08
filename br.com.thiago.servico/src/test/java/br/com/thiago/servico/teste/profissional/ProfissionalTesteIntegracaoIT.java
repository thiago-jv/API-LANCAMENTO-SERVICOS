package br.com.thiago.servico.teste.profissional;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.servico.domain.model.Endereco;
import br.com.thiago.servico.domain.model.Profissional;
import br.com.thiago.servico.domain.service.ProfissionalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfissionalTesteIntegracaoIT {

	@Autowired
	private ProfissionalService profissionalService;

	@Test
	public void salvar() {
		Profissional profissional = new Profissional();
		
		profissional.setNome("Jessica Henrique Araujo da Castro");
		profissional.setContato("9");
		profissional.setEmail("jessica.henrique.araujo.castro@hotmail.com");
		profissional.setIdade("1989-01-01");
		
		Endereco endereco = new Endereco();
		endereco.setBairro("Monte das oliveiras");
		endereco.setCep("69093118");
		endereco.setNumero("674");
		endereco.setComplemento("Aguas");
		endereco.setCidade("Manaus");
		endereco.setEstado("Amazonas");
		endereco.setLogradouro("Proximo Aguas do Amazonas");
		profissional.setEndereco(endereco);
			
		List<Profissional> lista = new ArrayList<>();
		lista.add(profissional);
		
		for (Profissional p : lista) {
			profissionalService.salvar(p);
		}
	}
}

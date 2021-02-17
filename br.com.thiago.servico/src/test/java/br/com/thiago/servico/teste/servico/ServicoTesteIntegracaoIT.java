package br.com.thiago.servico.teste.servico;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.servico.model.Cliente;
import br.com.thiago.servico.model.Profissional;
import br.com.thiago.servico.model.Servico;
import br.com.thiago.servico.model.StatusServico;
import br.com.thiago.servico.repository.ServicoRepository;
import br.com.thiago.servico.service.ClienteService;
import br.com.thiago.servico.service.ProfissionalService;
import br.com.thiago.servico.service.ServicoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicoTesteIntegracaoIT {

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private ServicoRepository servicoRepository;

	LocalDate dataPendencia = LocalDate.of(2021, Month.FEBRUARY, 15);
	LocalDate datafechamento = LocalDate.of(2021, Month.FEBRUARY, 16);

	@Test
	public void salvar() {
		Servico servico = new Servico();
		
		servico.setDescricao("Manutenção na mesa do pé quebrado");

		Profissional profissional = profissionalService.buscarOuFalhar(1L);
		servico.setProfissional(profissional);
		
		Cliente cliente = clienteService.buscarOuFalhar(1L);
		servico.setCliente(cliente);

		List<Servico> lista = new ArrayList<>();
		lista.add(servico);

		for (Servico s : lista) {
			servicoService.salvar(s);
		}
	}

	@Test
	public void muda_Status_Servico_Para_Pendente() {
		Servico servico = servicoRepository.findOne(1l);

		servico.setObservacao("Irá demorar um pouso o serviço");
		servico.pendente();

		Profissional profissional = profissionalService.buscarOuFalhar(7L);
		servico.setProfissional(profissional);

		servicoService.salvar(1L, servico);
	}

	@Test
	public void muda_Status_Servico_Para_Fechado() {
		Servico servico1 = servicoRepository.findOne(1l);

		servico1.setObservacao("O serviço foi fechado com sucesso!");
		servico1.fechado();

		Profissional profissional = profissionalService.buscarOuFalhar(1L);
		servico1.setProfissional(profissional);

		servicoService.salvar(1L, servico1);
	}

	@Test
	public void listaPorStatusProfissionalAberto() {		
		servicoRepository.buscaServicoProfissionalStatus("Jessica Henrique Araujo de Castro", StatusServico.ABERTO);
	}

}

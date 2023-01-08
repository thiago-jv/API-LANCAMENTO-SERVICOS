package br.com.thiago.servico.teste.equipamento;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.servico.controle.v1.converter.EquipamentoMapper;
import br.com.thiago.servico.controle.v1.converter.TipoEquipamentoMapper;
import br.com.thiago.servico.controle.v1.dto.id.TipoEquipamentoIdDTO;
import br.com.thiago.servico.controle.v1.dto.request.EquipamentoPostDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.servico.domain.model.Equipamento;
import br.com.thiago.servico.domain.model.TipoEquipamento;
import br.com.thiago.servico.domain.service.EquipamentoService;
import br.com.thiago.servico.domain.service.TipoEquipamentoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipamentoTesteIntegracaoIT {

	@Autowired
	private TipoEquipamentoService tipoEquipamentoService;
	
	@Autowired
	private EquipamentoService equipamentoService;

	@Test
	public void salvar() {
		var tipoEquipamento = new TipoEquipamento();
		tipoEquipamento.setDescricao("madeira");
		var tipoEquipamentoSalvo = tipoEquipamentoService.salvar(tipoEquipamento);

		var equipamento = new Equipamento();
		equipamento.setDescricao("Mesa");
		equipamento.setTipoEquipamento(tipoEquipamentoSalvo);
		
		List<Equipamento> lista = new ArrayList<>();
		lista.add(equipamento);
		
		for (Equipamento e : lista) {
			equipamentoService.salvar(e);
		}
	}
}

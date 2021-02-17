package br.com.thiago.servico.teste.equipamento;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.servico.model.Equipamento;
import br.com.thiago.servico.model.TipoEquipamento;
import br.com.thiago.servico.service.EquipamentoService;
import br.com.thiago.servico.service.TipoEquipamentoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipamentoTesteIntegracaoIT {

	@Autowired
	private TipoEquipamentoService tipoEquipamentoService;
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@Test
	public void salvar() {
		Equipamento equipamento = new Equipamento();
		
		equipamento.setDescricao("Mesa");
		TipoEquipamento tipoEquipamento1 = tipoEquipamentoService.buscarOuFalhar(1L);
		equipamento.setTipoEquipamento(tipoEquipamento1);
		
		List<Equipamento> lista = new ArrayList<>();
		lista.add(equipamento);
		
		for (Equipamento e : lista) {
			equipamentoService.salvar(e);
		}
	}
}

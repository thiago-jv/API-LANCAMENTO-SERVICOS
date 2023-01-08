package br.com.thiago.servico.teste.tipo.equipamento;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.servico.domain.model.TipoEquipamento;
import br.com.thiago.servico.domain.service.TipoEquipamentoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TipoEquipamentoTesteIntegracaoIT {

	@Autowired
	private TipoEquipamentoService tipoEquipamentoService;

	@Test
	public void salvar() {
		var tipoEquipamento = new TipoEquipamento();
		tipoEquipamento.setDescricao("Leve");
	
		List<TipoEquipamento> lista = new ArrayList<TipoEquipamento>();
		lista.add(tipoEquipamento);

		for (TipoEquipamento t : lista) {
			tipoEquipamentoService.salvar(t);
		}
	}
}

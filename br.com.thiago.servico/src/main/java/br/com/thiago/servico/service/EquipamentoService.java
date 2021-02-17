package br.com.thiago.servico.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.exception.EquipamentoNaoEncontradoException;
import br.com.thiago.servico.model.Equipamento;
import br.com.thiago.servico.model.TipoEquipamento;
import br.com.thiago.servico.repository.EquipamentoRepository;

@Service
public class EquipamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	@Autowired
	private TipoEquipamentoService tipoEquipamentoService;

	@Transactional
	public Equipamento salvar(Equipamento equipamento) {

		Long codigoTipoEquipamento = equipamento.getTipoEquipamento().getCodigo();
		TipoEquipamento tipoEquipamentoNovo = tipoEquipamentoService.buscarOuFalhar(codigoTipoEquipamento);
		equipamento.setTipoEquipamento(tipoEquipamentoNovo);

		return equipamentoRepository.save(equipamento);
	}

	public Equipamento buscarOuFalhar(Long codigo) {
		Equipamento equipamentoRetorno = equipamentoRepository.findOne(codigo);
		if (equipamentoRetorno == null) {
			throw new EquipamentoNaoEncontradoException(codigo);
		}
		return equipamentoRetorno;
	}
}

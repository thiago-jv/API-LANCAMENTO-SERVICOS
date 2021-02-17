package br.com.thiago.servico.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.exception.TipoEquipamentoNaoEncontradoException;
import br.com.thiago.servico.model.TipoEquipamento;
import br.com.thiago.servico.repository.TipoEquipamentoRepository;

@Service
public class TipoEquipamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoEquipamentoRepository tipoEquipamentoRepository;

	@Transactional
	public TipoEquipamento salvar(TipoEquipamento tipoEquipamento) {
		return tipoEquipamentoRepository.save(tipoEquipamento);
	}

	public TipoEquipamento buscarOuFalhar(Long codigo) {
		TipoEquipamento tipoEquipamentoRetorno = tipoEquipamentoRepository.findOne(codigo);
		if (tipoEquipamentoRetorno == null) {
			throw new TipoEquipamentoNaoEncontradoException(codigo);
		}
		return tipoEquipamentoRetorno;
	}
}

package br.com.thiago.servico.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.exception.ProfissionalNaoEncontradoException;
import br.com.thiago.servico.model.Profissional;
import br.com.thiago.servico.repository.ProfissionalRepository;

@Service
public class ProfissionalService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Transactional
	public Profissional salvar(Profissional profissional) {
		return profissionalRepository.save(profissional);
	}
	
	public Profissional buscarOuFalhar(Long codigo) {
		Profissional profissionalRetorno = profissionalRepository.findOne(codigo);
		if (profissionalRetorno == null) {
			throw new ProfissionalNaoEncontradoException(codigo);
		}
		return profissionalRetorno;
	}
}

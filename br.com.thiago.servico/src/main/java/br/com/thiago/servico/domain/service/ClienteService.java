package br.com.thiago.servico.domain.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.api.exception.ClienteNaoEncontradoException;
import br.com.thiago.servico.domain.model.Cliente;
import br.com.thiago.servico.domain.repository.ClienteRepository;

@Service
public class ClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente buscarOuFalhar(Long codigo) {
		return clienteRepository.findById(codigo).orElseThrow(() -> new ClienteNaoEncontradoException(codigo));
	}
}

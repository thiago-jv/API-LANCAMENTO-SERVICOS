package br.com.thiago.servico.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.exception.ClienteNaoEncontradoException;
import br.com.thiago.servico.model.Cliente;
import br.com.thiago.servico.repository.ClienteRepository;

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
		Cliente clienteRetorno = clienteRepository.findOne(codigo);
		if (clienteRetorno == null) {
			throw new ClienteNaoEncontradoException(codigo);
		}
		return clienteRetorno;
	}
}

package br.com.thiago.servico.domain.service;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.api.exception.ServicoNaoEncontradoException;
import br.com.thiago.servico.domain.model.Cliente;
import br.com.thiago.servico.domain.model.Profissional;
import br.com.thiago.servico.domain.model.Servico;
import br.com.thiago.servico.domain.repository.ServicoRepository;

@Service
public class ServicoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ClienteService clienteService;

	@Transactional
	public Servico salvar(Servico servico) {
		Long codigoProfissional = servico.getProfissional().getCodigo();
		Profissional profissional = profissionalService.buscarOuFalhar(codigoProfissional);
		servico.setProfissional(profissional);

		Long codigoCliente = servico.getCliente().getCodigo();
		Cliente cliente = clienteService.buscarOuFalhar(codigoCliente);
		servico.setCliente(cliente);

		return servicoRepository.save(servico);
	}

	@Transactional
	public Servico salvar(Long codigo, Servico servico) {
		var servicoSalvo = buscarOuFalhar(codigo);
		BeanUtils.copyProperties(servico, servicoSalvo, "codigo");
		var profissional = profissionalService.buscarOuFalhar(servico.getProfissional().getCodigo().longValue());
		servicoSalvo.setProfissional(profissional);
		var cliente = clienteService.buscarOuFalhar(servico.getCliente().getCodigo().longValue());
		servicoSalvo.setCliente(cliente);
		return servicoRepository.save(servicoSalvo);
	}

	@Transactional
	public void pendente(Long codigo, Long codigoProfissional) {
		var servicoAtual = servicoRepository.findById(codigo).orElseThrow(() -> new ServicoNaoEncontradoException(codigo));
		servicoAtual.setProfissional(profissionalService.buscarOuFalhar(codigoProfissional));
		servicoAtual.pendente();
	}

	@Transactional
	public void fechado(Long codigo, Long codigoProfissional) {
		var servicoAtual = servicoRepository.findById(codigo).orElseThrow(() -> new ServicoNaoEncontradoException(codigo));
		servicoAtual.setProfissional(profissionalService.buscarOuFalhar(codigoProfissional));
		servicoAtual.fechado();
	}
	
	public Servico buscarOuFalhar(Long codigo) {
		return servicoRepository.findById(codigo).orElseThrow(() -> new ServicoNaoEncontradoException(codigo));
	}
}

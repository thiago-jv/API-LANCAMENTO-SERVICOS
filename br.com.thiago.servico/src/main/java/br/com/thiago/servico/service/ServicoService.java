package br.com.thiago.servico.service;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.exception.ServicoNaoEncontradoException;
import br.com.thiago.servico.model.Cliente;
import br.com.thiago.servico.model.Profissional;
import br.com.thiago.servico.model.Servico;
import br.com.thiago.servico.repository.ServicoRepository;

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
		Servico servicoSalvo = buscarOuFalhar(codigo);
       
		BeanUtils.copyProperties(servico, servicoSalvo, "codigo");

		Long codigoProfissional = servico.getProfissional().getCodigo();
		Profissional profissional = profissionalService.buscarOuFalhar(codigoProfissional);
		servicoSalvo.setProfissional(profissional);

		Long codigoCliente = servico.getCliente().getCodigo();
		Cliente cliente = clienteService.buscarOuFalhar(codigoCliente);
		servicoSalvo.setCliente(cliente);

		return servicoRepository.save(servicoSalvo);
	}

	@Transactional
	public void pendente(Long codigo, Long codigoProfissional) {
		Servico servicoAtual = servicoRepository.findOne(codigo);

		Profissional profissional = profissionalService.buscarOuFalhar(codigoProfissional);
		servicoAtual.setProfissional(profissional);

		servicoAtual.pendente();
	}

	@Transactional
	public void fechado(Long codigo, Long codigoProfissional) {
		Servico servicoAtual = servicoRepository.findOne(codigo);

		Profissional profissional = profissionalService.buscarOuFalhar(codigoProfissional);
		servicoAtual.setProfissional(profissional);

		servicoAtual.fechado();
	}
	
	public Servico buscarOuFalhar(Long codigo) {
		Servico servicoRetorno = servicoRepository.findOne(codigo);
		if (servicoRetorno == null) {
			throw new ServicoNaoEncontradoException(codigo);
		}
		return servicoRetorno;
	}
}

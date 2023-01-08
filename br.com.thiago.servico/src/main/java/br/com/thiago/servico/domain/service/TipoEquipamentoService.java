package br.com.thiago.servico.domain.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.api.exception.TipoEquipamentoNaoEncontradoException;
import br.com.thiago.servico.domain.model.TipoEquipamento;
import br.com.thiago.servico.domain.repository.TipoEquipamentoRepository;

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
        return tipoEquipamentoRepository.findById(codigo).orElseThrow(() -> new TipoEquipamentoNaoEncontradoException(codigo));
    }
}

package br.com.thiago.servico.domain.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.servico.api.exception.EquipamentoNaoEncontradoException;
import br.com.thiago.servico.domain.model.Equipamento;
import br.com.thiago.servico.domain.model.TipoEquipamento;
import br.com.thiago.servico.domain.repository.EquipamentoRepository;

@Service
public class EquipamentoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private TipoEquipamentoService tipoEquipamentoService;

    @Transactional
    public Equipamento salvar(Equipamento equipamento) {
        Optional<TipoEquipamento> optionalTipoEquipamento = Optional.ofNullable(tipoEquipamentoService.buscarOuFalhar(equipamento.getTipoEquipamento().getCodigo().longValue()));
        equipamento.setTipoEquipamento(optionalTipoEquipamento.get());
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento buscarOuFalhar(Long codigo) {
        return equipamentoRepository.findById(codigo).orElseThrow(() -> new EquipamentoNaoEncontradoException(codigo));
    }
}

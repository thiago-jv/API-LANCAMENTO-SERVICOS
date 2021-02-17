package br.com.thiago.servico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.servico.model.TipoEquipamento;

@Repository
public interface TipoEquipamentoRepository extends JpaRepository<TipoEquipamento, Long>{

}

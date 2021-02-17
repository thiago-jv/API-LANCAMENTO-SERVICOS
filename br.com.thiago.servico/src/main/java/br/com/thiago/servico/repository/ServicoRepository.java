package br.com.thiago.servico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.thiago.servico.model.Servico;
import br.com.thiago.servico.model.StatusServico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	@Query("select s from Servico s inner join fetch s.profissional p where p.nome like %:nome% and s.status = :status")
	List<Servico> buscaServicoProfissionalStatus(@Param("nome") String nome, @Param("status") StatusServico status);
	
}

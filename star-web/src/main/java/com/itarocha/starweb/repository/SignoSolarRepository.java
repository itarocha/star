package com.itarocha.starweb.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itarocha.starweb.model.SignoSolar;

public interface SignoSolarRepository extends JpaRepository<SignoSolar, Long> {
	
	/*
	public Page<SignoSolar> findByClienteNomeContainingOrderByDataConsultaDesc(String nome, Pageable pageable);		

	public List<SignoSolar> findByClienteOrderByDataConsultaDesc(SignoSolar cliente);		
	
	//@Query("select p from pessoa p where (p.visitante = ?1 and p.voluntario = ?2) and ( (p.visita is null) or ((p.visita != null) and (p.visita.status in ?3)) ) order by p.nome")
	//public Page<Pessoa> findByVisitanteAndVoluntario(Boolean isVisitante, Boolean isVoluntario, Collection<StatusVisita> status, Pageable pageable);

	@Query("select c from consulta c where (c.cliente = ?1 and c.realizada = ?2 ) order by c.dataConsulta")
	public List<SignoSolar> findByClienteAndRealizada(SignoSolar cliente, String realizada);
	*/
	
}

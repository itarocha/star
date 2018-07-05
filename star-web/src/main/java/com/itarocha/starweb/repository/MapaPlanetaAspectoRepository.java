package com.itarocha.starweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itarocha.starweb.model.MapaPlanetaAspecto;

public interface MapaPlanetaAspectoRepository extends JpaRepository<MapaPlanetaAspecto, Long> {
	
	
	public Page<MapaPlanetaAspecto> findAll(Pageable pageable);		

	@Query("select o from MapaPlanetaAspecto o order by o.planetaOrigem, o.aspecto, o.planetaDestino")
	public List<MapaPlanetaAspecto> findAll();		

	@Query("select o from MapaPlanetaAspecto o WHERE o.tipoRelacao = 'MESTRE' order by o.planetaOrigem, o.aspecto, o.planetaDestino")
	public List<MapaPlanetaAspecto> findAllMaster();		

	/*
	public List<SignoSolar> findByClienteOrderByDataConsultaDesc(SignoSolar cliente);		
	
	//@Query("select p from pessoa p where (p.visitante = ?1 and p.voluntario = ?2) and ( (p.visita is null) or ((p.visita != null) and (p.visita.status in ?3)) ) order by p.nome")
	//public Page<Pessoa> findByVisitanteAndVoluntario(Boolean isVisitante, Boolean isVoluntario, Collection<StatusVisita> status, Pageable pageable);

	@Query("select c from consulta c where (c.cliente = ?1 and c.realizada = ?2 ) order by c.dataConsulta")
	public List<SignoSolar> findByClienteAndRealizada(SignoSolar cliente, String realizada);
	*/
	
}

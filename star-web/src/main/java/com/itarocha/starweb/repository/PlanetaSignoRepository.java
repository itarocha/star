package com.itarocha.starweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itarocha.starweb.model.PlanetaSigno;

public interface PlanetaSignoRepository extends JpaRepository<PlanetaSigno, Long> {
	
	
	public Page<PlanetaSigno> findAllOrderByPlanetaAndSigno(Pageable pageable);		

	@Query("select o from PlanetaSigno o order by o.planeta, o.signo")
	public List<PlanetaSigno> findAllOrderByPlanetaAndSigno();		


	/*
	public List<SignoSolar> findByClienteOrderByDataConsultaDesc(SignoSolar cliente);		
	
	//@Query("select p from pessoa p where (p.visitante = ?1 and p.voluntario = ?2) and ( (p.visita is null) or ((p.visita != null) and (p.visita.status in ?3)) ) order by p.nome")
	//public Page<Pessoa> findByVisitanteAndVoluntario(Boolean isVisitante, Boolean isVoluntario, Collection<StatusVisita> status, Pageable pageable);

	@Query("select c from consulta c where (c.cliente = ?1 and c.realizada = ?2 ) order by c.dataConsulta")
	public List<SignoSolar> findByClienteAndRealizada(SignoSolar cliente, String realizada);
	*/
	
}

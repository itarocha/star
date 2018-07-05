package com.itarocha.starweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itarocha.starweb.model.PlanetaCasa;

public interface PlanetaCasaRepository extends JpaRepository<PlanetaCasa, Long> {
	
	
	public Page<PlanetaCasa> findAllOrderByPlanetaAndCasa(Pageable pageable);		

	@Query("select o from PlanetaCasa o order by o.planeta, o.casa")
	public List<PlanetaCasa> findAllOrderByPlanetaAndCasa();		
	
}

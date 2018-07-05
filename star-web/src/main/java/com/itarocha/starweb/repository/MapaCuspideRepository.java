package com.itarocha.starweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itarocha.starweb.model.MapaCuspide;

public interface MapaCuspideRepository extends JpaRepository<MapaCuspide, Long> {
	
	
	//public Page<MapaCuspide> findAllOrderBySignoAndCasa(Pageable pageable);		

	//@Query("select mc from MapaCuspide mc order by mc.signo, mc.casa")
//	public List<MapaCuspide> findAllOrderBySignoAndCasa();		
	
	//@Query("select mc from MapaCuspide mc order by mc.signo, mc.casa")
	//public List<MapaCuspide> findAllOrderBySigno();		
	
	@Query("select o from MapaCuspide o order by o.signo, o.casa") 
	public List<MapaCuspide> findAllOrderBySignoAndCasa();		

	
}

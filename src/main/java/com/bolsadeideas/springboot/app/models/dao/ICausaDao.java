package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Causa;


public interface ICausaDao extends PagingAndSortingRepository<Causa, Long> {
	
	@Query("SELECT c FROM Causa c ORDER BY c.id DESC")
	public List<Causa> findAllByDesc();
	
	@Query("SELECT c FROM Causa c WHERE (c.numExpediente) LIKE %?1%"
			+ "OR lower(c.caratula) LIKE %?1%"
			+ "OR lower(c.victima) LIKE %?1%"
			+ "OR lower(c.victimario) LIKE %?1%"
			+ "OR lower(c.denunciante) LIKE %?1%"
			)
	public Page<Causa> findAll(Pageable pageable , String palabraClave);
	
	

}

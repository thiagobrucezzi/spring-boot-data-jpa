package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Causa;
import com.bolsadeideas.springboot.app.models.entity.LlamadaTelefonica;
import com.bolsadeideas.springboot.app.models.entity.MovimientoBancario;
import com.bolsadeideas.springboot.app.models.entity.RedSocial;

public interface ICausaService {
	public List<Causa> findAllByDesc();
	
	public Page<Causa> findAll(Pageable pageable);
	
	public boolean save2(Causa causa);
	
	public void save(Causa causa);
	
	public Causa findOne(Long id);
	
	public void delete(Long id);	
	
	public Causa save3(Causa causa);
	
	public Page<Causa> findAll(Pageable pageable,String palabraClave);
	
	public List<Causa> listAll(String palabraClave);
	
	public void guardarLlamada(LlamadaTelefonica llamada);
	
	public void guardarMov(MovimientoBancario movimiento);
	
	public void guardarRed(RedSocial red);
	


}

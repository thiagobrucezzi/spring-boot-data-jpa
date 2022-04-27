package com.bolsadeideas.springboot.app.models.service;


import com.bolsadeideas.springboot.app.models.entity.LlamadaTelefonica;

public interface ILlamadaTelefonicaService {
	
	public void save(LlamadaTelefonica llamada);
	
	public LlamadaTelefonica create(LlamadaTelefonica llamada);
	
	public LlamadaTelefonica getLlamadaTelefonica(Long id);
	
	public void delete(Long id);
	

}

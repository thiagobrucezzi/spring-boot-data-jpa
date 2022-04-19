package com.bolsadeideas.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.ILlamadaTelefonicaDao;
import com.bolsadeideas.springboot.app.models.entity.LlamadaTelefonica;

@Service
public class LlamadaTelefonicaServiceImp implements ILlamadaTelefonicaService {
	@Autowired
	private ILlamadaTelefonicaDao llamadaDao;
	
	@Override
	@Transactional
	public void save(LlamadaTelefonica llamada) {
		llamadaDao.save(llamada);
	}

}
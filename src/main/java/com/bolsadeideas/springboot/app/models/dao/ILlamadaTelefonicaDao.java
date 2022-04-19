package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.LlamadaTelefonica;

public interface ILlamadaTelefonicaDao extends PagingAndSortingRepository<LlamadaTelefonica, Long> {
	

}

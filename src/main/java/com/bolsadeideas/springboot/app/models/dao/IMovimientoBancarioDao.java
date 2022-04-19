package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.MovimientoBancario;

public interface IMovimientoBancarioDao extends PagingAndSortingRepository<MovimientoBancario, Long> {

}

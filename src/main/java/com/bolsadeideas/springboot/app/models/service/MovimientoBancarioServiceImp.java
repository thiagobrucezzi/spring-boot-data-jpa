package com.bolsadeideas.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IMovimientoBancarioDao;
import com.bolsadeideas.springboot.app.models.entity.MovimientoBancario;

@Service
public class MovimientoBancarioServiceImp implements IMovimientoBancarioService {
	
	@Autowired
	private IMovimientoBancarioDao movimientoBancarioDao;
	
	
	@Override
	@Transactional
	public MovimientoBancario create(MovimientoBancario mov) {
		mov=movimientoBancarioDao.save(mov);
		return mov;
	}


	@Override
	public MovimientoBancario getMovimientoBancario(Long id) {
		return movimientoBancarioDao.findById(id).get();
	}


	@Override
	public void delete(Long id) {
		movimientoBancarioDao.deleteById(id);
		
	}

}

package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.MovimientoBancario;

public interface IMovimientoBancarioService {

	public MovimientoBancario create(MovimientoBancario mov);

	public MovimientoBancario getMovimientoBancario(Long id);

	public void delete(Long id);

}

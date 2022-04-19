package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity(name = "movimiento_bancario")
public class MovimientoBancario extends Informacion {

	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String cuenta;
	@NotEmpty
	private String usuario;
	@NotEmpty
	private String tipoTransaccion;
	@NotEmpty
	private double monto;
	@NotEmpty
	private String moneda;
	@NotEmpty
	private String datoCajero;
	
	//Constructor
	public MovimientoBancario() {
	}

	//Setters and getters
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getDatoCajero() {
		return datoCajero;
	}

	public void setDatoCajero(String datoCajero) {
		this.datoCajero = datoCajero;
	}

	@Override
	public void setDescripcion(String descripcion) {
		super.setDescripcion(descripcion);
	}

	@Override
	public String tipo() {
		return "Movimiento bancario";
	}

}

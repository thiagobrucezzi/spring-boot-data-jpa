package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.Entity;

@Entity(name = "movimiento_bancario")
public class MovimientoBancario extends Informacion {

	private static final long serialVersionUID = 1L;
	private String cuenta;
	private String usuario;
	private String tipoTransaccion;
	private double monto;
	private String moneda;
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

}

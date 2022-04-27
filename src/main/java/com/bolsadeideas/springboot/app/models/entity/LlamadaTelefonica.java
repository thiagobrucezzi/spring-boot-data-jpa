package com.bolsadeideas.springboot.app.models.entity;



import javax.persistence.Entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity(name = "llamada")
public class LlamadaTelefonica extends Informacion {

	
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String direccionIp;
	
	@NotEmpty
	@Length(max = 10, min = 10, message = "Debe ingresar 10 digitos")
	private String numeroReceptor;
	
	@NotEmpty
	@Length(max = 10, min = 10, message = "Debe ingresar 10 digitos")
	private String numeroEmisor;
	
	@NotNull
	private Integer duracion;
	
	public LlamadaTelefonica() {
		 this.setEsLlamada(true);
	     this.setEsMovimiento(false);
	     this.setEsRedSocial(false);
		
	}


	// Setters and getters
	public String getNumeroReceptor() {
		return numeroReceptor;
	}

	public void setNumeroReceptor(String numeroReceptor) {
		this.numeroReceptor = numeroReceptor;
	}

	public String getNumeroEmisor() {
		return numeroEmisor;
	}

	public void setNumeroEmisor(String numeroEmisor) {
		this.numeroEmisor = numeroEmisor;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getDireccionIp() {
		return direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}


}

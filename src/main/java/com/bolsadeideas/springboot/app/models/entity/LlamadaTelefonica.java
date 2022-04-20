package com.bolsadeideas.springboot.app.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

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
	
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fecha;

	// Constructor
	


	// Setters and getters
	public String getNumeroReceptor() {
		return numeroReceptor;
	}

	public LlamadaTelefonica() {
		
	}

	public LlamadaTelefonica(@NotEmpty String direccionIp,
			@NotEmpty @Length(max = 10, min = 10, message = "Debe ingresar 10 digitos") String numeroReceptor,
			@NotEmpty @Length(max = 10, min = 10, message = "Debe ingresar 10 digitos") String numeroEmisor,
			@NotNull Integer duracion, Date fecha) {
		
		this.direccionIp = direccionIp;
		this.numeroReceptor = numeroReceptor;
		this.numeroEmisor = numeroEmisor;
		this.duracion = duracion;
		this.fecha = fecha;
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
	

	@Override
	public void setDescripcion(String descripcion) {
		super.setDescripcion(descripcion);
	}


	@Override
	public String tipo() {
		return "Llamada telefónica";
	}
	@Override
	public void setCausa(Causa causa) {
		// TODO Auto-generated method stub
		super.setCausa(causa);
	}



}

package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Informacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date fecha;

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "causa_id")
	private Causa causa;

	@NotNull
	private String descripcion;
	
	private Boolean esLlamada;
	private Boolean esMovimiento;
	private Boolean esRedSocial;


	//Constructores 
	
	public Informacion() {

	}

	
	//Setters and getters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Causa getCausa() {
		return causa;
	}

	public void setCausa(Causa causa) {
		this.causa = causa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Boolean getEsLlamada() {
		return esLlamada;
	}


	public void setEsLlamada(Boolean esLlamada) {
		this.esLlamada = esLlamada;
	}


	public Boolean getEsMovimiento() {
		return esMovimiento;
	}


	public void setEsMovimiento(Boolean esMovimiento) {
		this.esMovimiento = esMovimiento;
	}


	public Boolean getEsRedSocial() {
		return esRedSocial;
	}


	public void setEsRedSocial(Boolean esRedSocial) {
		this.esRedSocial = esRedSocial;
	}
	
	
	
}

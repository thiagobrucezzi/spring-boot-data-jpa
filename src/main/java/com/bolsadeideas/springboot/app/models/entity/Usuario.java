package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@Column(unique=true)
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Column(length=100)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@NotEmpty
	private String dependenciaPrestaciones;
	
	
	private String rol;
	
	private static final long serialVersionUID = 1L;

	//Setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDependenciaPrestaciones() {
		return dependenciaPrestaciones;
	}

	public void setDependenciaPrestaciones(String dependenciaPrestaciones) {
		this.dependenciaPrestaciones = dependenciaPrestaciones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRoles() {
		return rol;
	}

	public void setRoles(String roles) {
		this.rol = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
}

package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.bolsadeideas.springboot.app.enums.RolNombre;

@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames={"authority"})})
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(unique=true)
	private RolNombre authority;

	private static final long serialVersionUID = 1L;
	
	
	
	public Role() {
		super();
	}

	public Role(Long id, RolNombre authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	// Setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolNombre getAuthority() {
		return authority;
	}

	public void setAuthority(RolNombre authority) {
		this.authority = authority;
	}

}

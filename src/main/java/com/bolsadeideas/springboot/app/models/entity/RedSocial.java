package com.bolsadeideas.springboot.app.models.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity(name = "red_social")
public class RedSocial extends Informacion{
	
	private static final long serialVersionUID = 1L;
	private String tipo;
    private String perfil;
    
    //Constructores
    public RedSocial() {

    }

    //Setters and getters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    

	@Override
	public void setFecha(Date fecha) {
		super.setFecha(fecha);
	}

	@Override
	public void setDescripcion(String descripcion) {
		super.setDescripcion(descripcion);
	}

	@Override
	public String tipo() {
		return "Red social";
	}

}

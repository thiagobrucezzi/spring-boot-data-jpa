package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;


@Entity(name = "redSocial")
public class RedSocial extends Informacion{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String tipo;
	
	@NotEmpty
    private String perfil;
    
    //Constructores
    public RedSocial() {
    	this.setEsLlamada(false);
	     this.setEsMovimiento(false);
	     this.setEsRedSocial(true);

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
    

	
}

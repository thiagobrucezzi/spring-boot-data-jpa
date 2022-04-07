package com.bolsadeideas.springboot.app.models.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity(name = "red_social")
public class RedSocial extends Informacion{
	
	private static final long serialVersionUID = 1L;
	private String tipo;
    private String perfil;
    private String descripcion;
    
    //Constructores
    public RedSocial(Date fecha, String tipo, String perfil, String descripcion) {
        super(fecha);
        this.tipo = tipo;
        this.perfil = perfil;
        this.descripcion = descripcion;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

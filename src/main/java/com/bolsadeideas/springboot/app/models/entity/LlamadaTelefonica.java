package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "llamada_telefonica")
public class LlamadaTelefonica extends Informacion {
	
	private static final long serialVersionUID = 1L;
	@NotEmpty
    private String numeroReceptor;
    @NotEmpty
    private String numeroEmisor;
    @NotNull
    private Integer duracion;

    //Constructor
    public LlamadaTelefonica() {

    }

    //Setters and getters
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

}

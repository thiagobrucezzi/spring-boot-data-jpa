package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="causas")
public class Causa implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(max = 21,min = 21,message = "Debe ingresar 21 digitos")
	private String numExpediente;
    
    @NotEmpty
    private String caratula;
    
    @NotEmpty
    private String denunciante;
    
    @NotEmpty
    private String victima;
    @NotEmpty
    private String victimario;
    @NotEmpty
    private String contexto;
    
    private Boolean estado;

    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha;
    
    @OneToMany(mappedBy = "causa"  ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Informacion> informaciones;
    
    
    //Constructor 
    public Causa() {
    	this.informaciones=new ArrayList<Informacion>();
    	this.fecha=new Date();
        this.estado=true;
    	
    }
    
	//Setters and getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }
    
       
    public String getDenunciante() {
		return denunciante;
	}

	public void setDenunciante(String denunciante) {
		this.denunciante = denunciante;
	}

	public String getCaratula() {
		return caratula;
	}


	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}


	public String getVictima() {
        return victima;
    }

    public void setVictima(String victima) {
        this.victima = victima;
    }

    public String getVictimario() {
        return victimario;
    }

    public void setVictimario(String victimario) {
        this.victimario = victimario;
    }


    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

	public List<Informacion> getInformacion() {
		return informaciones;
	}

	public void setInformacion(List<Informacion> informacion) {
		this.informaciones = informacion;
	}
    
    public void addInformacion(Informacion info) {
    	informaciones.add(info);
    }

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
    

}

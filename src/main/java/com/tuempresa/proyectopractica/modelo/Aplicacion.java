package com.tuempresa.proyectopractica.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Aplicacion {
	
	@Id
    @Column(length=10)
    private String codigo;
    
    @Column(length=50)
    private String denominacion;

	@ManyToMany(mappedBy="aplicaciones")
	private Collection<Cliente> clientes;

	@Required
    @OneToOne(fetch=FetchType.LAZY, optional = true)
    private Licencia licencia;

	

}

package com.tuempresa.proyectopractica.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Cliente {
	
	@Id
	@Column(length=10)
	private String codigo;
	
	@Column(length=50)
	private String denominacion;

	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="CLIENTE_APLICACION",
        joinColumns=@JoinColumn(name="cliente_id"),
        inverseJoinColumns=@JoinColumn(name="aplicacion_id")
    )
	private Collection<Aplicacion> aplicaciones;
}

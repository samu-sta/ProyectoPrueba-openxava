package com.tuempresa.registrodeaplicaciones.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;


@Entity @Getter @Setter
@Views({
    @View(name="Simple", members="codigo; denominacion"),
    @View(members="codigo; denominacion; clientes")
})
public class Aplicacion {
	
	@Id
    @Column(length=10)
    private String codigo;
    
    @Column(length=50)
    private String denominacion;

	@ManyToMany(mappedBy="aplicaciones")
	private Collection<Cliente> clientes;

	

	

}

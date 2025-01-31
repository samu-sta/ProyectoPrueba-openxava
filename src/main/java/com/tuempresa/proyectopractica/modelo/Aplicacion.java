package com.tuempresa.proyectopractica.modelo;

import java.util.*;

import javax.persistence.*;

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

}

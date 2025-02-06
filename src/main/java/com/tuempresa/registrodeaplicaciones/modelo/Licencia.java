package com.tuempresa.registrodeaplicaciones.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
@Tab(
    properties="codigo, cliente.denominacion, aplicacion.denominacion, fechaDeExpiracion, activa"
)
@Entity @Getter @Setter
public class Licencia {
    @Id
    @Column(length=10)
    private String codigo; 

    private Date fechaDeExpiracion;

    @Column(length=50)
    private String observaciones;

    @Required
    @OneToOne(fetch=FetchType.LAZY, optional = true)
    private Aplicacion aplicacion;

    @Required
    @OneToOne(fetch=FetchType.LAZY, optional = true)
    private Cliente cliente;

    @Required
    private Boolean activa;
}
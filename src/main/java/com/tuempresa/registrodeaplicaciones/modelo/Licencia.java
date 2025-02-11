package com.tuempresa.registrodeaplicaciones.modelo;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.tuempresa.registrodeaplicaciones.listeners.*;

import lombok.*;
@Tab(
    properties="codigo, cliente.denominacion, aplicacion.denominacion, importe, fechaDeExpiracion, activa"
)
@Entity @Getter @Setter
@EntityListeners(LicenciaListener.class)
public class Licencia {
    @Id
    @Column(length=10)
    private String codigo; 

    private Date fechaDeExpiracion;

    @Column(length=50)
    private String observaciones;

    @Required
    @ReferenceView("Simple")
    @OneToOne(fetch=FetchType.LAZY, optional = true)
    private Aplicacion aplicacion;

    @Required
    @ReferenceView("Simple")
    @OneToOne(fetch=FetchType.LAZY, optional = true)
    private Cliente cliente;

    @Required
    private Boolean activa;

    @Required
    private BigDecimal importe;




}
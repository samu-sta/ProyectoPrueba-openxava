package com.tuempresa.proyectopractica.modelo;

import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity @Getter @Setter
public class Licencia {
    @Id
    @Column(length=10)
    private String id; 

    private Date fechaExpiracion;

    @Column(length=50)
    private String observaciones;

    private boolean activa;

}
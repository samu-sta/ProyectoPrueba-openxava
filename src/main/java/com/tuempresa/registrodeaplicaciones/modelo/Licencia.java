package com.tuempresa.registrodeaplicaciones.modelo;

import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity @Getter @Setter
public class Licencia {
    @Id
    @Column(length=10)
    private String codigo; 

    private Date fechaDeExpiracion;

    @Column(length=50)
    private String observaciones;

    private boolean activa;

}
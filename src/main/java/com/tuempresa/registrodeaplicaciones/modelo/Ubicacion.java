package com.tuempresa.registrodeaplicaciones.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.openxava.annotations.Editor;

import lombok.*;

@Entity @Getter @Setter
public class Ubicacion {
  
  @Id @Column(length = 36)
  private String id;

  @Editor("mapaEditor")
  private String coordenadas;
  
}

package com.tuempresa.registrodeaplicaciones.modelo;

import java.math.BigDecimal;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class LicenciaPorMes {

    private int mes;
    
    private BigDecimal importe;

    private long cantidad;
}

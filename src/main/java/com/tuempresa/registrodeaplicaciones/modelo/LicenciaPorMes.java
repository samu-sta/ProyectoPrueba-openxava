package com.tuempresa.registrodeaplicaciones.modelo;

import java.math.BigDecimal;

import lombok.*;

@Getter @Setter
public class LicenciaPorMes {

    private int mes;
    
    private BigDecimal importe;

    private long cantidad;


    public LicenciaPorMes(int mes, BigDecimal importe, long cantidad) {
        this.mes = mes;
        this.importe = importe != null ? importe : BigDecimal.ZERO;
        this.cantidad = cantidad;
    }

    // Constructor por defecto requerido por JPA
    public LicenciaPorMes() {
        this.importe = BigDecimal.ZERO;
        this.cantidad = 0;
    }
    
}

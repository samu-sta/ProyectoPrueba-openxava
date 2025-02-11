package com.tuempresa.registrodeaplicaciones.modelo;

import java.math.*;
import java.util.Collection;


import org.openxava.annotations.*;

import lombok.*;

@View(members="dineroTotalLicencias, numeroDeLicencias; evolucionLicencias; mesesTopLicencias")
@Getter @Setter
public class LicenciaDashboard {
	
    @LargeDisplay(icon="cash")
    @Money
    private BigDecimal dineroTotalLicencias;


    @LargeDisplay(icon="chart-line")
    private int numeroDeLicencias;
    

    @Chart(labelProperties = "mes", dataProperties = "importe")
    private Collection<LicenciaPorMes> evolucionLicencias;
    
    

    @SimpleList
    @ListProperties("mes, importe")
    private Collection<LicenciaPorMes> mesesTopLicencias;
    

}

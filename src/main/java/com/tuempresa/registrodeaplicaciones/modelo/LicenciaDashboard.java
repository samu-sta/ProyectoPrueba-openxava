package com.tuempresa.registrodeaplicaciones.modelo;

import java.math.*;
import java.util.Collection;

import javax.persistence.TypedQuery;

import org.openxava.annotations.*;
import org.openxava.jpa.XPersistence;

@View(members="dineroTotalLicencias, numeroDeLicencias; evolucionLicencias; mesesTopLicencias")
public class LicenciaDashboard {
	
    @LargeDisplay(icon="cash")
    @Money
    public BigDecimal getDineroTotalLicencias() {
        BigDecimal total = XPersistence.getManager()
            .createQuery("select sum(l.importe) from Licencia l", BigDecimal.class)
            .getSingleResult();
        
        return total != null ? total : BigDecimal.ZERO;
    }


    @LargeDisplay(icon="chart-line")
    public int getNumeroDeLicencias() {
        return XPersistence.getManager()
            .createQuery("select count(l) from Licencia l", Long.class)
            .getSingleResult()
            .intValue();
    }

    @Chart(labelProperties = "mes", dataProperties = "importe")
    public Collection<LicenciaPorMes> getEvolucionLicencias() {
        String jpql = "SELECT new com.tuempresa.registrodeaplicaciones.modelo.LicenciaPorMes("
            + "MONTH(l.fechaDeExpiracion), "
            + "SUM(l.importe), "
            + "COUNT(l)) "
            + "FROM Licencia l "
            + "WHERE l.fechaDeExpiracion IS NOT NULL "
            + "GROUP BY MONTH(l.fechaDeExpiracion) "
            + "ORDER BY MONTH(l.fechaDeExpiracion)";
            
        TypedQuery<LicenciaPorMes> query = XPersistence.getManager()
            .createQuery(jpql, LicenciaPorMes.class);
            
        return query.getResultList();
    }

    @SimpleList
    @ListProperties("mes, importe")
    public Collection<LicenciaPorMes> getMesesTopLicencias() {
        String jpql = "SELECT new com.tuempresa.registrodeaplicaciones.modelo.LicenciaPorMes("
            + "MONTH(l.fechaDeExpiracion), "
            + "SUM(l.importe), "
            + "COUNT(l)) "
            + "FROM Licencia l "
            + "WHERE l.fechaDeExpiracion IS NOT NULL "
            + "GROUP BY MONTH(l.fechaDeExpiracion) "
            + "ORDER BY SUM(l.importe) DESC";
            
        TypedQuery<LicenciaPorMes> query = XPersistence.getManager()
            .createQuery(jpql, LicenciaPorMes.class)
            .setMaxResults(5);
            
        return query.getResultList();
    }

}

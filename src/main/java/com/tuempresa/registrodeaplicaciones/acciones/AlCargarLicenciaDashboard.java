package com.tuempresa.registrodeaplicaciones.acciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.TypedQuery;

import org.openxava.actions.ViewBaseAction;
import org.openxava.jpa.XPersistence;

import com.tuempresa.registrodeaplicaciones.modelo.LicenciaDashboard;
import com.tuempresa.registrodeaplicaciones.modelo.LicenciaPorMes;

public class AlCargarLicenciaDashboard extends ViewBaseAction{
    

    @Override
    public void execute() throws Exception {

        // Cargar datos básicos
        LicenciaDashboard dashboard = (LicenciaDashboard) getView().getModel();
        if (dashboard == null) {
            dashboard = new LicenciaDashboard();
        }
                
        dashboard.setDineroTotalLicencias(getDineroTotalLicencias());
        dashboard.setNumeroDeLicencias(getNumeroDeLicencias());
        // Establecer colecciones
        dashboard.setEvolucionLicencias(getEvolucionLicencias());
        dashboard.setMesesTopLicencias(getMesesTopLicencias());
        
        // Actualizar la vista con el modelo modificado
        getView().setModel(dashboard);

        
    }

    public BigDecimal getDineroTotalLicencias() {
        BigDecimal total = XPersistence.getManager()
            .createQuery("select sum(l.importe) from Licencia l", BigDecimal.class)
            .getSingleResult();
        
        return total != null ? total : BigDecimal.ZERO;
    }

    public int getNumeroDeLicencias() {
        return XPersistence.getManager()
            .createQuery("select count(l) from Licencia l", Long.class)
            .getSingleResult()
            .intValue();
    }

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

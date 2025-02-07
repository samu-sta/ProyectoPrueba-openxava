package com.tuempresa.registrodeaplicaciones.acciones;

import java.util.*;
import org.openxava.actions.*;
import org.openxava.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import com.tuempresa.registrodeaplicaciones.modelo.*;

public class GenerarReporteLicenciasAccion extends JasperReportBaseAction {

    private Cliente cliente;
    
    @Override
    protected JRDataSource getDataSource() throws Exception {
        cliente = getCliente();
        if (cliente == null) {
            throw new Exception("Cliente no encontrado");
        }
        return new JRBeanCollectionDataSource(cliente.getLicencias());
    }

    @Override
    protected String getJRXML() throws Exception {
        return "GenerarReporte.jrxml";
    }

    @Override
    protected Map getParameters() throws Exception {
        Map parameters = new HashMap();
        cliente = getCliente();
        if (cliente == null) {
            return parameters;    
        }
        parameters.put("denominacion", cliente.getDenominacion());
        parameters.put("email", cliente.getEmail());
        return parameters;
    }
    
    private Cliente getCliente() {
        if (cliente != null) return cliente;

        try {
            cliente = (Cliente) MapFacade.findEntity("Cliente", getView().getKeyValues());
        } catch (Exception e) {
            addError("Error al obtener el cliente: " + e.getMessage());
        }
        return cliente;
    }
}
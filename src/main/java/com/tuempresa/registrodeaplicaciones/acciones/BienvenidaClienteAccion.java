package com.tuempresa.registrodeaplicaciones.acciones;

import org.openxava.actions.*;

import com.tuempresa.registrodeaplicaciones.modelo.*;
import com.tuempresa.registrodeaplicaciones.servicios.*;

public class BienvenidaClienteAccion extends OnChangePropertyBaseAction {
    
    private EmailSenderService emailService = new EmailSenderService();
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void execute() throws Exception {
        if (cliente == null || cliente.getEmail() == null) {
            addError("Email del cliente no disponible");
            return;
        }

        String subject = "Bienvenido a nuestro sistema";
        String message = String.format(
            "Estimado cliente %s,\n\n" +
            "Bienvenido a nuestro sistema de registro de aplicaciones.\n" +
            "Su código de cliente es: %s\n\n" +
            "Saludos cordiales.",
            cliente.getDenominacion(),
            cliente.getCodigo()
        );

        emailService.sendEmail(cliente.getEmail(), subject, message);
        addMessage("Email de bienvenida enviado a " + cliente.getEmail());
    }
}
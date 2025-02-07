package com.tuempresa.registrodeaplicaciones.listeners;

import java.util.*;

import javax.persistence.*;

import org.apache.commons.logging.*;

import com.ibm.icu.text.*;
import com.tuempresa.registrodeaplicaciones.modelo.*;
import com.tuempresa.registrodeaplicaciones.servicios.*;

public class LicenciaListener {

    private static final Log log = LogFactory.getLog(LicenciaListener.class);
    
    @PostLoad
    public void checkLicenciaExpiracion(Licencia licencia) {
        log.info("Verificando expiración de licencia para: " + licencia.getAplicacion().getDenominacion());

        if (licencia.getFechaDeExpiracion() == null || !licencia.getActiva()) {
            log.info("Licencia sin fecha de expiraci�n o inactiva");
            return;
        }

        Calendar oneWeekFromNow = Calendar.getInstance();
        oneWeekFromNow.add(Calendar.DATE, 7);
        
        log.info("Fecha de expiraci�n: " + licencia.getFechaDeExpiracion());
        
        if (licencia.getFechaDeExpiracion().before(oneWeekFromNow.getTime())) {
            try {

                EmailSenderService emailService = new EmailSenderService();
                String to = licencia.getCliente().getEmail();
                String subject = "Aviso: Su licencia expirar� pronto";
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
                String body = String.format(
                    "Estimado cliente %s,\n\n" +
                    "Su licencia para la aplicaci�n %s expirar� el %s.\n" +
                    "Por favor, contacte con nosotros para renovarla.\n\n" +
                    "Saludos cordiales.",
                    licencia.getCliente().getDenominacion(),
                    licencia.getAplicacion().getDenominacion(),
                    dateFormat.format(licencia.getFechaDeExpiracion())
                );
                
                emailService.sendEmail(to, subject, body);
            } catch (Exception e) {
                System.err.println("Error al enviar el correo de notificación: " + e.getMessage());
                e.printStackTrace();
            }
        }else {
        	log.info("La licencia no expira pronto");
        }
    }
}
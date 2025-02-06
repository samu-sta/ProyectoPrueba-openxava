package com.tuempresa.registrodeaplicaciones.servicios;



import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.logging.*;

public class EmailSenderService {
    private static Log log = LogFactory.getLog(EmailSenderService.class);
    private final Properties properties = new Properties();
    private Session session;
    private Properties emailConfig;
    
    private String user;
    private String password;
    
    public EmailSenderService() {
        loadEmailConfig();
        user = emailConfig.getProperty("mail.smtp.user");
        password = emailConfig.getProperty("mail.smtp.password");
    }
    
    private void loadEmailConfig() {
        try {
            emailConfig = new Properties();
            emailConfig.load(getClass().getResourceAsStream("/email.properties"));
        } catch (IOException e) {
            log.error("Error loading email configuration", e);
        }
    }
    
    private void init() {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        

        if (user == null || password == null) {
            throw new RuntimeException("Email configuration missing");
        }

        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        if (to == null || to.trim().isEmpty()) {
            throw new MessagingException("Recipient email address is required");
        }
        init();
       
        
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject != null ? subject : "");
        message.setText(body != null ? body : "");

        Transport.send(message);
    }
}
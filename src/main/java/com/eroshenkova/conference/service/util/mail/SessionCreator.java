package com.eroshenkova.conference.service.util.mail;

import com.eroshenkova.conference.constant.Mail;
import com.eroshenkova.conference.resource.MailManager;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class SessionCreator {
    private String username;
    private String password;
    private Properties sessionProperties;

    SessionCreator() {
        String smtpHost = MailManager.getProperty(Mail.SMTP_HOST);
        String smtpPort = MailManager.getProperty(Mail.SMTP_PORT);
        username = MailManager.getProperty(Mail.USERNAME);
        password = MailManager.getProperty(Mail.PASSWORD);
        sessionProperties = new Properties();
        sessionProperties.put(Mail.SMTP_HOST, smtpHost);
        sessionProperties.put(Mail.SMTP_SOCKETFACTORY_PORT, smtpPort);
        sessionProperties.put(Mail.SMTP_SOCKETFACTORY_CLASS, Mail.SSLSOCKETFACTORY);
        sessionProperties.put(Mail.AUTENTIFICATION, "true");
        sessionProperties.put(Mail.SMTP_PORT, smtpPort);
    }

    Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    String getUsername() {
        return username;
    }
}

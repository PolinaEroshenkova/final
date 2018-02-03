package com.eroshenkova.conference.mail;

import com.eroshenkova.conference.resource.MailManager;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {
    private String username;
    private String password;
    private Properties sessionProperties;

    public SessionCreator() {
        String smtpHost = MailManager.getProperty("mail.smtp.host");
        String smtpPort = MailManager.getProperty("mail.smtp.port");
        username = MailManager.getProperty("mail.user.name");
        password = MailManager.getProperty("mail.user.password");
        sessionProperties = new Properties();
        sessionProperties.put("mail.smtp.host", smtpHost);
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", smtpPort);
    }

    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
}

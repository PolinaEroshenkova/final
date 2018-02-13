package com.eroshenkova.conference.service.util.mail;

import com.eroshenkova.conference.constant.Mail;
import com.eroshenkova.conference.resource.MailManager;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Creates mail session
 *
 * @author Palina Yerashenkava
 */
class SessionCreator {
    /**
     * Username of senders account
     */
    private String username;
    /**
     * Password of senders account
     */
    private String password;
    /**
     * Properties for defining mail behavior
     */
    private Properties sessionProperties;

    /**
     * Basic session creator constructor works with mail.properties
     */
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

    /**
     * Creates new mail session. Uses username and password for authenticator
     * @return mail session
     */
    Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    /**
     * @return username of account
     */
    String getUsername() {
        return username;
    }
}

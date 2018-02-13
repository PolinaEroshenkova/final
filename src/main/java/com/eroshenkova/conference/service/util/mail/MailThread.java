package com.eroshenkova.conference.service.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Thread of mail to not to delay program performance
 *
 * @author Palina Yerashenkava
 * @see Thread
 */
public class MailThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(MailThread.class);

    /**
     * Defines mail object
     */
    private MimeMessage message;

    /**
     * Defines recipient of email
     */
    private String recipient;

    /**
     * Defines subject of email
     */
    private String subject;

    /**
     * Defines email text
     */
    private String text;

    /**
     * Basic constructor
     *
     * @param recipient is recipient email address
     * @param subject   is mail subject
     * @param text      is mail text
     */
    MailThread(String recipient, String subject, String text) {
        this.recipient = recipient;
        this.subject = subject;
        this.text = text;
    }

    /**
     * Defines original mail initialization
     */
    private void init() {
        SessionCreator sessionCreator = new SessionCreator();
        Session mailSession = sessionCreator.createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress(sessionCreator.getUsername()));
            message.setSubject(subject);
            message.setText(text);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "email address +" + recipient + " is incorrect");
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error forming");
        }
    }

    /**
     * Overrides thread run method to define thread behavior
     */
    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error while send email");
        }
    }
}

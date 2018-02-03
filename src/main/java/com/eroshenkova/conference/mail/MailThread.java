package com.eroshenkova.conference.mail;

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

public class MailThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(MailThread.class);

    private MimeMessage message;
    private String sender;
    private String recipient;
    private String subject;
    private String text;

    public MailThread(String sender, String recipient, String subject, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.text = text;
    }

    private void init() {
        Session mailSession = (new SessionCreator()).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress(sender));
            message.setSubject(subject);
            message.setText(text);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "email address +" + recipient + " is incorrect");
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error forming");
        }
    }

    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error while send email");
        }
    }
}

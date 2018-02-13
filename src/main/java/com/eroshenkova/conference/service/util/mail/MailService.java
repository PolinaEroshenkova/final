package com.eroshenkova.conference.service.util.mail;

import com.eroshenkova.conference.constant.Parameter;

/**
 * Defines methods to work with email
 *
 * @author Palina Yerashenkava
 */
public class MailService {

    /**
     * Sends email to address
     * @param answer is answer to email
     * @param email is receiver email
     */
    public void sendEmail(String answer, String email) {
        MailThread mail = new MailThread(email, Parameter.EMAIL_SUBJECT, answer);
        mail.start();
    }
}

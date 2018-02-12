package com.eroshenkova.conference.service.util.mail;

import com.eroshenkova.conference.constant.Parameter;

public class MailService {

    public void sendEmail(String answer, String email) {
        MailThread mail = new MailThread(email, Parameter.EMAIL_SUBJECT, answer);
        mail.start();
    }
}

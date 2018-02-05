package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.mail.MailThread;

public class MailLogic {

    public void sendEmail(String answer, String email) {
        MailThread mail = new MailThread(email, Parameter.EMAIL_SUBJECT, answer);
        mail.start();
    }
}

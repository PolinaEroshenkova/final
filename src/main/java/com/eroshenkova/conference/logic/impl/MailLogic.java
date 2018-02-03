package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.mail.MailThread;

public class MailLogic {

    public void sendEmail(MailThread mail) {
        mail.start();
    }
}

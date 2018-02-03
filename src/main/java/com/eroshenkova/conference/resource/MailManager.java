package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

public class MailManager {

    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.mail");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

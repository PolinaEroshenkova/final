package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.messages");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

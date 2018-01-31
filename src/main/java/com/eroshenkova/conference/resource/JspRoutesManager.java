package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

public class JspRoutesManager {
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.jsproutes");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

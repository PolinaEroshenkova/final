package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

public class UrlManager {
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.url");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

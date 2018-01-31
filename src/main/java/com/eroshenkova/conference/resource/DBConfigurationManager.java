package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

public class DBConfigurationManager {

    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.dbconfiguration");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

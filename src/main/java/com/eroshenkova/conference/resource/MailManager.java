package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

/**
 * Manager that works with mail configuration properties
 *
 * @author Palina Yerashenkava
 */
public class MailManager {

    /**
     * Defines resource bundle
     */
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.mail");

    /**
     * @param key is key which helps to find in bundle value
     * @return string representation of this value
     */
    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

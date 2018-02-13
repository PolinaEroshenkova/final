package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

/**
 * Manager that works with url configuration properties
 *
 * @author Palina Yerashenkava
 */
public class UrlManager {
    /**
     * Defines resource bundle
     */
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.url");

    /**
     * @param key is key which helps to find in bundle value
     * @return string representation of this value
     */
    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

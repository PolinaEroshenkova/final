package com.eroshenkova.conference.resource;

import java.util.ResourceBundle;

/**
 * Manager that works with jsproutes configuration properties
 *
 * @author Palina Yerashenkava
 */
public class JspRoutesManager {
    /**
     * Defines resource bundle
     */
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("properties.jsproutes");

    /**
     * @param key is key which helps to find in bundle value
     * @return string representation of this value
     */
    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}

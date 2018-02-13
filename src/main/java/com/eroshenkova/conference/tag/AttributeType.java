package com.eroshenkova.conference.tag;

/**
 * Defines type for attribute of tag
 *
 * @author Palina Yerashenkava
 */
public enum AttributeType {
    /**
     * Defines date
     */
    DATE("date"),
    /**
     * Defines date and time
     */
    DATETIME("datetime");

    /**
     * Defines type of enum
     */
    private String type;

    /**
     * Constructor to define type of enum
     * @param type is enum type
     */
    AttributeType(String type) {
        this.type = type;
    }

    /**
     * @return type of enum
     */
    public String getType() {
        return type;
    }

    /**
     * @param type is enum type
     */
    public void setType(String type) {
        this.type = type;
    }
}

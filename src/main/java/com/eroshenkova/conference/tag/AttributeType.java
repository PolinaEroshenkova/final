package com.eroshenkova.conference.tag;

public enum AttributeType {
    DATE("date"), DATETIME("datetime");

    private String type;

    AttributeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

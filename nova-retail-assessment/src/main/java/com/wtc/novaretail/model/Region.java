package com.wtc.novaretail.model;

public class Region {

    private final String code;
    private final String name;

    public Region(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isSameRegion(Region other) {
        if (other == null) {
            return false;
        }
        return this.code.equalsIgnoreCase(other.code);
    }
}

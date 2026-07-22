package com.wtc.northbridge.model;

public class Branch {

    private final String code;
    private final String name;

    public Branch(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isSameBranch(Branch other) {
        if (other == null) {
            return false;
        }
        return this.code.equalsIgnoreCase(other.code);
    }
}

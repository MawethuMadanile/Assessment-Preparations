package com.wtc.northbridge.model;

import java.util.List;
import java.util.Set;

public class Applicant {

    private final String id;
    private final String fullName;
    private final int creditScore;
    private final boolean employed;
    private final boolean inArrears;
    private final Set<String> heldProductTypes;
    private final Branch branch;

    public Applicant(String id, String fullName, int creditScore, boolean employed, boolean inArrears,
                      Set<String> heldProductTypes, Branch branch) {
        this.id = id;
        this.fullName = fullName;
        this.creditScore = creditScore;
        this.employed = employed;
        this.inArrears = inArrears;
        this.heldProductTypes = heldProductTypes;
        this.branch = branch;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public boolean isEmployed() {
        return employed;
    }

    public boolean isInArrears() {
        return inArrears;
    }

    public Branch getBranch() {
        return branch;
    }

    public boolean hasHeldAll(List<String> requiredProductTypes) {
        if (requiredProductTypes == null || requiredProductTypes.isEmpty()) {
            return true;
        }
        return heldProductTypes != null && heldProductTypes.containsAll(requiredProductTypes);
    }
}

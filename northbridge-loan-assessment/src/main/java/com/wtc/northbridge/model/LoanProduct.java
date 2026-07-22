package com.wtc.northbridge.model;

import java.time.LocalDateTime;
import java.util.List;

public class LoanProduct {

    private final String title;
    private final int minimumCreditScore;
    private final List<String> requiredProductTypes;
    private final Branch branch;
    private final boolean openToAllBranches;
    private final boolean closedForApplications;
    private final LocalDateTime documentDeadline;
    private final int gracePeriodMinutes;
    private final List<String> allowedDocumentExtensions;
    private final double maxFileSizeMb;

    public LoanProduct(String title, int minimumCreditScore, List<String> requiredProductTypes, Branch branch,
                        boolean openToAllBranches, boolean closedForApplications, LocalDateTime documentDeadline,
                        int gracePeriodMinutes, List<String> allowedDocumentExtensions, double maxFileSizeMb) {
        this.title = title;
        this.minimumCreditScore = minimumCreditScore;
        this.requiredProductTypes = requiredProductTypes;
        this.branch = branch;
        this.openToAllBranches = openToAllBranches;
        this.closedForApplications = closedForApplications;
        this.documentDeadline = documentDeadline;
        this.gracePeriodMinutes = gracePeriodMinutes;
        this.allowedDocumentExtensions = allowedDocumentExtensions;
        this.maxFileSizeMb = maxFileSizeMb;
    }

    public String getTitle() {
        return title;
    }

    public int getMinimumCreditScore() {
        return minimumCreditScore;
    }

    public List<String> getRequiredProductTypes() {
        return requiredProductTypes;
    }

    public Branch getBranch() {
        return branch;
    }

    public boolean isOpenToAllBranches() {
        return openToAllBranches;
    }

    public boolean isClosedForApplications() {
        return closedForApplications;
    }

    public LocalDateTime getDocumentDeadline() {
        return documentDeadline;
    }

    public int getGracePeriodMinutes() {
        return gracePeriodMinutes;
    }

    public List<String> getAllowedDocumentExtensions() {
        return allowedDocumentExtensions;
    }

    public double getMaxFileSizeMb() {
        return maxFileSizeMb;
    }
}

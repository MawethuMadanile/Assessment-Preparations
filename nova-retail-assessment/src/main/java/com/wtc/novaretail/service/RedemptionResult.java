package com.wtc.novaretail.service;

import java.util.List;

public class RedemptionResult {

    private final boolean successful;
    private final List<String> issues;

    public RedemptionResult(boolean successful, List<String> issues) {
        this.successful = successful;
        this.issues = issues;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public List<String> getIssues() {
        return issues;
    }
}

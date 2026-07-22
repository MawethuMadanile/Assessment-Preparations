package com.wtc.northbridge.service;

import java.util.List;

public class SubmissionOutcome {

    private final boolean accepted;
    private final List<String> issues;

    public SubmissionOutcome(boolean accepted, List<String> issues) {
        this.accepted = accepted;
        this.issues = issues;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public List<String> getIssues() {
        return issues;
    }
}

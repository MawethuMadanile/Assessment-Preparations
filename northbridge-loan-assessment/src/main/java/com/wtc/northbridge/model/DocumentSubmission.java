package com.wtc.northbridge.model;

import java.time.LocalDateTime;
import java.util.List;

public class DocumentSubmission {

    private final LocalDateTime submittedAt;
    private final List<SupportingDocument> documents;

    public DocumentSubmission(LocalDateTime submittedAt, List<SupportingDocument> documents) {
        this.submittedAt = submittedAt;
        this.documents = documents;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public List<SupportingDocument> getDocuments() {
        return documents;
    }
}

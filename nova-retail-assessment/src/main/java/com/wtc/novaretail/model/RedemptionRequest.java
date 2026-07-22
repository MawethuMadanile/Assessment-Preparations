package com.wtc.novaretail.model;

import java.time.LocalDateTime;
import java.util.List;

public class RedemptionRequest {

    private final LocalDateTime submittedAt;
    private final List<ReceiptFile> files;

    public RedemptionRequest(LocalDateTime submittedAt, List<ReceiptFile> files) {
        this.submittedAt = submittedAt;
        this.files = files;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public List<ReceiptFile> getFiles() {
        return files;
    }
}

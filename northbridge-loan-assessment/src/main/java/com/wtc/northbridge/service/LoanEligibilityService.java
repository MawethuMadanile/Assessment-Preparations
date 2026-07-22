package com.wtc.northbridge.service;

import com.wtc.northbridge.model.Applicant;
import com.wtc.northbridge.model.DocumentSubmission;
import com.wtc.northbridge.model.LoanProduct;
import com.wtc.northbridge.model.SupportingDocument;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoanEligibilityService {

    public boolean isPreApproved(Applicant applicant, LoanProduct product) {
        if (applicant.isEmployed() && !applicant.isInArrears() && applicant.getCreditScore() >= product.getMinimumCreditScore() && applicant.hasHeldAll(product.getRequiredProductTypes()) && (product.isOpenToAllBranches() || applicant.getBranch().isSameBranch(product.getBranch())) && !product.isClosedForApplications()) {
            return true;
        }
        return false;
    }

    public SubmissionOutcome processDocumentSubmission(Applicant applicant, LoanProduct product, DocumentSubmission submission) {
        List<String> issues = new ArrayList<>();

        if (submission.getSubmittedAt() != null) {
            if (submission.getSubmittedAt().isAfter(product.getDocumentDeadline())) {
                if (product.getGracePeriodMinutes() > 0) {
                    long minutesLate = Duration.between(product.getDocumentDeadline(), submission.getSubmittedAt()).toMinutes();
                    if (minutesLate > product.getGracePeriodMinutes()) {
                        issues.add("Submission by " + applicant.getFullName() + " is " + minutesLate
                                + " minutes late, exceeding the grace period for " + product.getTitle());
                    }
                } else {
                    issues.add("Submission by " + applicant.getFullName() + " is late and " + product.getTitle()
                            + " has no grace period");
                }
            }
        }

        if (submission.getDocuments() != null && !submission.getDocuments().isEmpty()) {
            for (SupportingDocument document : submission.getDocuments()) {
                if (!product.getAllowedDocumentExtensions().contains(document.getExtension())) {
                    if (document.getSizeInMb() > product.getMaxFileSizeMb()) {
                        issues.add(document.getFileName() + " has a disallowed extension and exceeds the "
                                + product.getMaxFileSizeMb() + "MB limit");
                    } else {
                        issues.add(document.getFileName() + " has a disallowed extension");
                    }
                } else if (document.getSizeInMb() > product.getMaxFileSizeMb()) {
                    issues.add(document.getFileName() + " exceeds the " + product.getMaxFileSizeMb() + "MB limit");
                }
            }
        } else {
            issues.add("No supporting documents were submitted for " + product.getTitle());
        }

        return new SubmissionOutcome(issues.isEmpty(), issues);
    }
}

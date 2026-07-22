package com.wtc.northbridge;

import com.wtc.northbridge.model.Applicant;
import com.wtc.northbridge.model.Branch;
import com.wtc.northbridge.model.DocumentSubmission;
import com.wtc.northbridge.model.LoanProduct;
import com.wtc.northbridge.model.SupportingDocument;
import com.wtc.northbridge.service.LoanEligibilityService;
import com.wtc.northbridge.service.SubmissionOutcome;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Branch highStreet = new Branch("HS", "High Street Branch");

        Applicant applicant = new Applicant("A-100", "Priya Nair", 720, true, false,
                Set.of("CURRENT_ACCOUNT", "SAVINGS"), highStreet);

        LoanProduct product = new LoanProduct("Personal Loan - Standard", 650, List.of("CURRENT_ACCOUNT"),
                highStreet, false, false, LocalDateTime.now().plusDays(3), 60,
                List.of("pdf", "jpg", "png"), 8.0);

        LoanEligibilityService service = new LoanEligibilityService();

        boolean preApproved = service.isPreApproved(applicant, product);
        System.out.println("Pre-approved: " + preApproved);

        DocumentSubmission submission = new DocumentSubmission(LocalDateTime.now(),
                List.of(new SupportingDocument("payslip.pdf", "pdf", 2.1)));

        SubmissionOutcome outcome = service.processDocumentSubmission(applicant, product, submission);
        System.out.println("Accepted: " + outcome.isAccepted());
        System.out.println("Issues: " + outcome.getIssues());
    }
}

package com.wtc.northbridge.service;

import com.wtc.northbridge.model.Applicant;
import com.wtc.northbridge.model.Branch;
import com.wtc.northbridge.model.DocumentSubmission;
import com.wtc.northbridge.model.LoanProduct;
import com.wtc.northbridge.model.SupportingDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanEligibilityServiceTest {

    private LoanEligibilityService service;
    private Branch highStreet;
    private Branch citySouth;
    private LoanProduct product;
    private Applicant eligibleApplicant;

    @BeforeEach
    void setUp() {
        service = new LoanEligibilityService();
        highStreet = new Branch("HS", "High Street");
        citySouth = new Branch("CS", "City South");

        product = new LoanProduct("Personal Loan - Standard", 650, List.of("CURRENT_ACCOUNT"), highStreet,
                false, false, LocalDateTime.now().plusDays(3), 60, List.of("pdf", "jpg"), 8.0);

        eligibleApplicant = new Applicant("A-1", "Priya Nair", 720, true, false,
                Set.of("CURRENT_ACCOUNT"), highStreet);
    }

    // ---- isPreApproved ----

    @Test
    void applicantMeetingAllConditionsIsPreApproved() {
        assertTrue(service.isPreApproved(eligibleApplicant, product));
    }

    @Test
    void unemployedApplicantIsNotPreApproved() {
        Applicant unemployed = new Applicant("A-2", "Ben Cole", 720, false, false,
                Set.of("CURRENT_ACCOUNT"), highStreet);
        assertFalse(service.isPreApproved(unemployed, product));
    }

    @Test
    void applicantInArrearsIsNotPreApproved() {
        Applicant inArrears = new Applicant("A-3", "Chidi Adeyemi", 720, true, true,
                Set.of("CURRENT_ACCOUNT"), highStreet);
        assertFalse(service.isPreApproved(inArrears, product));
    }

    @Test
    void applicantBelowMinimumCreditScoreIsNotPreApproved() {
        Applicant lowScore = new Applicant("A-4", "Dana Fischer", 500, true, false,
                Set.of("CURRENT_ACCOUNT"), highStreet);
        assertFalse(service.isPreApproved(lowScore, product));
    }

    @Test
    void applicantMissingRequiredProductTypeIsNotPreApproved() {
        Applicant missingProduct = new Applicant("A-5", "Elena Popescu", 720, true, false,
                Set.of("SAVINGS"), highStreet);
        assertFalse(service.isPreApproved(missingProduct, product));
    }

    @Test
    void applicantAtDifferentBranchIsNotPreApprovedWhenProductIsBranchRestricted() {
        Applicant otherBranch = new Applicant("A-6", "Farid Haddad", 720, true, false,
                Set.of("CURRENT_ACCOUNT"), citySouth);
        assertFalse(service.isPreApproved(otherBranch, product));
    }

    @Test
    void applicantAtDifferentBranchIsPreApprovedWhenProductIsOpenToAllBranches() {
        Applicant otherBranch = new Applicant("A-6", "Farid Haddad", 720, true, false,
                Set.of("CURRENT_ACCOUNT"), citySouth);
        LoanProduct openProduct = new LoanProduct("Personal Loan - Standard", 650, List.of("CURRENT_ACCOUNT"),
                highStreet, true, false, LocalDateTime.now().plusDays(3), 60, List.of("pdf", "jpg"), 8.0);
        assertTrue(service.isPreApproved(otherBranch, openProduct));
    }

    @Test
    void productClosedForApplicationsIsNotAvailableEvenToAnEligibleApplicant() {
        LoanProduct closedProduct = new LoanProduct("Personal Loan - Standard", 650, List.of("CURRENT_ACCOUNT"),
                highStreet, false, true, LocalDateTime.now().plusDays(3), 60, List.of("pdf", "jpg"), 8.0);
        assertFalse(service.isPreApproved(eligibleApplicant, closedProduct));
    }

    // ---- processDocumentSubmission ----

    @Test
    void onTimeSubmissionWithValidDocumentIsAccepted() {
        DocumentSubmission submission = new DocumentSubmission(LocalDateTime.now(),
                List.of(new SupportingDocument("payslip.pdf", "pdf", 1.0)));

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, product, submission);

        assertTrue(outcome.isAccepted());
        assertTrue(outcome.getIssues().isEmpty());
    }

    @Test
    void submissionLateButWithinGracePeriodIsAccepted() {
        DocumentSubmission submission = new DocumentSubmission(product.getDocumentDeadline().plusMinutes(20),
                List.of(new SupportingDocument("payslip.pdf", "pdf", 1.0)));

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, product, submission);

        assertTrue(outcome.isAccepted());
    }

    @Test
    void submissionLateBeyondGracePeriodIsRejected() {
        DocumentSubmission submission = new DocumentSubmission(product.getDocumentDeadline().plusMinutes(90),
                List.of(new SupportingDocument("payslip.pdf", "pdf", 1.0)));

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, product, submission);

        assertFalse(outcome.isAccepted());
        assertEquals(1, outcome.getIssues().size());
    }

    @Test
    void lateSubmissionWithNoGracePeriodConfiguredIsRejected() {
        LoanProduct noGraceProduct = new LoanProduct("Personal Loan - Standard", 650, List.of("CURRENT_ACCOUNT"),
                highStreet, false, false, LocalDateTime.now().plusDays(3), 0, List.of("pdf", "jpg"), 8.0);
        DocumentSubmission submission = new DocumentSubmission(noGraceProduct.getDocumentDeadline().plusMinutes(5),
                List.of(new SupportingDocument("payslip.pdf", "pdf", 1.0)));

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, noGraceProduct, submission);

        assertFalse(outcome.isAccepted());
    }

    @Test
    void documentWithDisallowedExtensionIsRejected() {
        DocumentSubmission submission = new DocumentSubmission(LocalDateTime.now(),
                List.of(new SupportingDocument("payslip.docx", "docx", 1.0)));

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, product, submission);

        assertFalse(outcome.isAccepted());
        assertEquals(1, outcome.getIssues().size());
        assertTrue(outcome.getIssues().get(0).contains("disallowed extension"));
        assertFalse(outcome.getIssues().get(0).contains("exceeds"));
    }

    @Test
    void documentExceedingMaxSizeIsRejected() {
        DocumentSubmission submission = new DocumentSubmission(LocalDateTime.now(),
                List.of(new SupportingDocument("payslip.pdf", "pdf", 20.0)));

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, product, submission);

        assertFalse(outcome.isAccepted());
        assertTrue(outcome.getIssues().get(0).contains("exceeds"));
        assertFalse(outcome.getIssues().get(0).contains("disallowed"));
    }

    @Test
    void documentWithDisallowedExtensionAndOversizedProducesCombinedMessage() {
        DocumentSubmission submission = new DocumentSubmission(LocalDateTime.now(),
                List.of(new SupportingDocument("payslip.docx", "docx", 20.0)));

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, product, submission);

        assertFalse(outcome.isAccepted());
        assertTrue(outcome.getIssues().get(0).contains("disallowed extension and exceeds"));
    }

    @Test
    void submissionWithNoDocumentsIsRejected() {
        DocumentSubmission submission = new DocumentSubmission(LocalDateTime.now(), List.of());

        SubmissionOutcome outcome = service.processDocumentSubmission(eligibleApplicant, product, submission);

        assertFalse(outcome.isAccepted());
        assertTrue(outcome.getIssues().get(0).contains("No supporting documents"));
    }
}

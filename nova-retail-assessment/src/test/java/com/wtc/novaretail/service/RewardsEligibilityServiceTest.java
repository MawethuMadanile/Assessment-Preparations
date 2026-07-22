package com.wtc.novaretail.service;

import com.wtc.novaretail.model.Customer;
import com.wtc.novaretail.model.ReceiptFile;
import com.wtc.novaretail.model.RedemptionRequest;
import com.wtc.novaretail.model.Region;
import com.wtc.novaretail.model.Reward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RewardsEligibilityServiceTest {

    private RewardsEligibilityService service;
    private Region uk;
    private Region us;
    private Reward reward;
    private Customer eligibleCustomer;

    @BeforeEach
    void setUp() {
        service = new RewardsEligibilityService();
        uk = new Region("UK", "United Kingdom");
        us = new Region("US", "United States");

        reward = new Reward("Free Next-Day Delivery Voucher", 2, List.of("ELECTRONICS"), uk, false, false,
                LocalDateTime.now().plusDays(1), 30, List.of("pdf", "jpg"), 5.0);

        eligibleCustomer = new Customer("C-1", "Amara Okoye", 3, true, false, Set.of("ELECTRONICS"), uk);
    }

    // ---- canRedeemReward ----

    @Test
    void customerMeetingAllConditionsIsEligible() {
        assertTrue(service.canRedeemReward(eligibleCustomer, reward));
    }

    @Test
    void inactiveCustomerIsNotEligible() {
        Customer inactive = new Customer("C-2", "Ben Cole", 3, false, false, Set.of("ELECTRONICS"), uk);
        assertFalse(service.canRedeemReward(inactive, reward));
    }

    @Test
    void suspendedCustomerIsNotEligible() {
        Customer suspended = new Customer("C-3", "Chidi Adeyemi", 3, true, true, Set.of("ELECTRONICS"), uk);
        assertFalse(service.canRedeemReward(suspended, reward));
    }

    @Test
    void customerBelowMinimumTierIsNotEligible() {
        Customer lowTier = new Customer("C-4", "Dana Fischer", 1, true, false, Set.of("ELECTRONICS"), uk);
        assertFalse(service.canRedeemReward(lowTier, reward));
    }

    @Test
    void customerMissingRequiredPurchaseCategoryIsNotEligible() {
        Customer missingCategory = new Customer("C-5", "Elena Popescu", 3, true, false, Set.of("HOMEWARE"), uk);
        assertFalse(service.canRedeemReward(missingCategory, reward));
    }

    @Test
    void customerInDifferentRegionIsNotEligibleWhenRewardIsRegionRestricted() {
        Customer otherRegion = new Customer("C-6", "Farid Haddad", 3, true, false, Set.of("ELECTRONICS"), us);
        assertFalse(service.canRedeemReward(otherRegion, reward));
    }

    @Test
    void customerInDifferentRegionIsEligibleWhenRewardIsOpenToAllRegions() {
        Customer otherRegion = new Customer("C-6", "Farid Haddad", 3, true, false, Set.of("ELECTRONICS"), us);
        Reward openReward = new Reward("Free Next-Day Delivery Voucher", 2, List.of("ELECTRONICS"), uk, true, false,
                LocalDateTime.now().plusDays(1), 30, List.of("pdf", "jpg"), 5.0);
        assertTrue(service.canRedeemReward(otherRegion, openReward));
    }

    @Test
    void lockedRewardIsNotRedeemableEvenByAnEligibleCustomer() {
        Reward lockedReward = new Reward("Free Next-Day Delivery Voucher", 2, List.of("ELECTRONICS"), uk, false, true,
                LocalDateTime.now().plusDays(1), 30, List.of("pdf", "jpg"), 5.0);
        assertFalse(service.canRedeemReward(eligibleCustomer, lockedReward));
    }

    // ---- processRedemptionRequest ----

    @Test
    void onTimeRequestWithValidReceiptHasNoIssues() {
        RedemptionRequest request = new RedemptionRequest(LocalDateTime.now(),
                List.of(new ReceiptFile("receipt.pdf", "pdf", 1.0)));

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, reward, request);

        assertTrue(result.isSuccessful());
        assertTrue(result.getIssues().isEmpty());
    }

    @Test
    void requestSubmittedLateButWithinGracePeriodHasNoIssues() {
        RedemptionRequest request = new RedemptionRequest(reward.getClaimDeadline().plusMinutes(10),
                List.of(new ReceiptFile("receipt.pdf", "pdf", 1.0)));

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, reward, request);

        assertTrue(result.isSuccessful());
    }

    @Test
    void requestSubmittedLateBeyondGracePeriodIsFlagged() {
        RedemptionRequest request = new RedemptionRequest(reward.getClaimDeadline().plusMinutes(45),
                List.of(new ReceiptFile("receipt.pdf", "pdf", 1.0)));

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, reward, request);

        assertFalse(result.isSuccessful());
        assertEquals(1, result.getIssues().size());
    }

    @Test
    void lateRequestWithNoGracePeriodConfiguredIsFlagged() {
        Reward noGraceReward = new Reward("Free Next-Day Delivery Voucher", 2, List.of("ELECTRONICS"), uk, false, false,
                LocalDateTime.now().plusDays(1), 0, List.of("pdf", "jpg"), 5.0);
        RedemptionRequest request = new RedemptionRequest(noGraceReward.getClaimDeadline().plusMinutes(5),
                List.of(new ReceiptFile("receipt.pdf", "pdf", 1.0)));

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, noGraceReward, request);

        assertFalse(result.isSuccessful());
    }

    @Test
    void receiptWithDisallowedExtensionIsFlagged() {
        RedemptionRequest request = new RedemptionRequest(LocalDateTime.now(),
                List.of(new ReceiptFile("receipt.exe", "exe", 1.0)));

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, reward, request);

        assertFalse(result.isSuccessful());
        assertEquals(1, result.getIssues().size());
        assertTrue(result.getIssues().get(0).contains("disallowed extension"));
        assertFalse(result.getIssues().get(0).contains("exceeds"));
    }

    @Test
    void receiptExceedingMaxSizeIsFlagged() {
        RedemptionRequest request = new RedemptionRequest(LocalDateTime.now(),
                List.of(new ReceiptFile("receipt.pdf", "pdf", 9.0)));

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, reward, request);

        assertFalse(result.isSuccessful());
        assertTrue(result.getIssues().get(0).contains("exceeds"));
        assertFalse(result.getIssues().get(0).contains("disallowed"));
    }

    @Test
    void receiptWithDisallowedExtensionAndOversizedProducesCombinedMessage() {
        RedemptionRequest request = new RedemptionRequest(LocalDateTime.now(),
                List.of(new ReceiptFile("receipt.exe", "exe", 9.0)));

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, reward, request);

        assertFalse(result.isSuccessful());
        assertTrue(result.getIssues().get(0).contains("disallowed extension and exceeds"));
    }

    @Test
    void requestWithNoFilesIsFlagged() {
        RedemptionRequest request = new RedemptionRequest(LocalDateTime.now(), List.of());

        RedemptionResult result = service.processRedemptionRequest(eligibleCustomer, reward, request);

        assertFalse(result.isSuccessful());
        assertTrue(result.getIssues().get(0).contains("No receipt files"));
    }
}

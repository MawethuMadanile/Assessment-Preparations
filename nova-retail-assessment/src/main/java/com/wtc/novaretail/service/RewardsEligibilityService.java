package com.wtc.novaretail.service;

import com.wtc.novaretail.model.Customer;
import com.wtc.novaretail.model.ReceiptFile;
import com.wtc.novaretail.model.RedemptionRequest;
import com.wtc.novaretail.model.Reward;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RewardsEligibilityService {

    public boolean canRedeemReward(Customer customer, Reward reward) {
        if (customer.isActive() && !customer.isSuspended() && customer.getLoyaltyTierLevel() >= reward.getMinimumTierLevel() && customer.hasPurchasedAll(reward.getRequiredCategories()) && (reward.isOpenToAllRegions() || customer.getRegion().isSameRegion(reward.getRegion())) && !reward.isLocked()) {
            return true;
        }
        return false;
    }

    public RedemptionResult processRedemptionRequest(Customer customer, Reward reward, RedemptionRequest request) {
        List<String> issues = new ArrayList<>();

        if (request.getSubmittedAt() != null) {
            if (request.getSubmittedAt().isAfter(reward.getClaimDeadline())) {
                if (reward.getGracePeriodMinutes() > 0) {
                    long minutesLate = Duration.between(reward.getClaimDeadline(), request.getSubmittedAt()).toMinutes();
                    if (minutesLate > reward.getGracePeriodMinutes()) {
                        issues.add("Redemption request by " + customer.getFullName() + " is " + minutesLate
                                + " minutes late, exceeding the grace period for " + reward.getTitle());
                    }
                } else {
                    issues.add("Redemption request by " + customer.getFullName() + " is late and " + reward.getTitle()
                            + " has no grace period");
                }
            }
        }

        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            for (ReceiptFile file : request.getFiles()) {
                if (!reward.getAllowedReceiptExtensions().contains(file.getExtension())) {
                    if (file.getSizeInMb() > reward.getMaxFileSizeMb()) {
                        issues.add(file.getFileName() + " has a disallowed extension and exceeds the "
                                + reward.getMaxFileSizeMb() + "MB limit");
                    } else {
                        issues.add(file.getFileName() + " has a disallowed extension");
                    }
                } else if (file.getSizeInMb() > reward.getMaxFileSizeMb()) {
                    issues.add(file.getFileName() + " exceeds the " + reward.getMaxFileSizeMb() + "MB limit");
                }
            }
        } else {
            issues.add("No receipt files were submitted for " + reward.getTitle());
        }

        return new RedemptionResult(issues.isEmpty(), issues);
    }
}

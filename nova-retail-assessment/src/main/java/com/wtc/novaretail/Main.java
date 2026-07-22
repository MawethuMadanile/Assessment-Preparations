package com.wtc.novaretail;

import com.wtc.novaretail.model.Customer;
import com.wtc.novaretail.model.ReceiptFile;
import com.wtc.novaretail.model.RedemptionRequest;
import com.wtc.novaretail.model.Region;
import com.wtc.novaretail.model.Reward;
import com.wtc.novaretail.service.RedemptionResult;
import com.wtc.novaretail.service.RewardsEligibilityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Region uk = new Region("UK", "United Kingdom");

        Customer customer = new Customer("C-100", "Amara Okoye", 3, true, false,
                Set.of("ELECTRONICS", "HOMEWARE"), uk);

        Reward reward = new Reward("Free Next-Day Delivery Voucher", 2, List.of("ELECTRONICS"), uk, false, false,
                LocalDateTime.now().plusDays(1), 30, List.of("pdf", "jpg", "png"), 5.0);

        RewardsEligibilityService service = new RewardsEligibilityService();

        boolean eligible = service.canRedeemReward(customer, reward);
        System.out.println("Eligible to redeem: " + eligible);

        RedemptionRequest request = new RedemptionRequest(LocalDateTime.now(),
                List.of(new ReceiptFile("receipt.pdf", "pdf", 1.2)));

        RedemptionResult result = service.processRedemptionRequest(customer, reward, request);
        System.out.println("Redemption successful: " + result.isSuccessful());
        System.out.println("Issues: " + result.getIssues());
    }
}

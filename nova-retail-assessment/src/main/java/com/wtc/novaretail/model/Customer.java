package com.wtc.novaretail.model;

import java.util.List;
import java.util.Set;

public class Customer {

    private final String id;
    private final String fullName;
    private final int loyaltyTierLevel;
    private final boolean active;
    private final boolean suspended;
    private final Set<String> purchasedCategories;
    private final Region region;

    public Customer(String id, String fullName, int loyaltyTierLevel, boolean active, boolean suspended,
                     Set<String> purchasedCategories, Region region) {
        this.id = id;
        this.fullName = fullName;
        this.loyaltyTierLevel = loyaltyTierLevel;
        this.active = active;
        this.suspended = suspended;
        this.purchasedCategories = purchasedCategories;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getLoyaltyTierLevel() {
        return loyaltyTierLevel;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public Region getRegion() {
        return region;
    }

    public boolean hasPurchasedAll(List<String> requiredCategories) {
        if (requiredCategories == null || requiredCategories.isEmpty()) {
            return true;
        }
        return purchasedCategories != null && purchasedCategories.containsAll(requiredCategories);
    }
}

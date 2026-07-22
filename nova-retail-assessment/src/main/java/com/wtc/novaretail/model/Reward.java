package com.wtc.novaretail.model;

import java.time.LocalDateTime;
import java.util.List;

public class Reward {

    private final String title;
    private final int minimumTierLevel;
    private final List<String> requiredCategories;
    private final Region region;
    private final boolean openToAllRegions;
    private final boolean locked;
    private final LocalDateTime claimDeadline;
    private final int gracePeriodMinutes;
    private final List<String> allowedReceiptExtensions;
    private final double maxFileSizeMb;

    public Reward(String title, int minimumTierLevel, List<String> requiredCategories, Region region,
                   boolean openToAllRegions, boolean locked, LocalDateTime claimDeadline, int gracePeriodMinutes,
                   List<String> allowedReceiptExtensions, double maxFileSizeMb) {
        this.title = title;
        this.minimumTierLevel = minimumTierLevel;
        this.requiredCategories = requiredCategories;
        this.region = region;
        this.openToAllRegions = openToAllRegions;
        this.locked = locked;
        this.claimDeadline = claimDeadline;
        this.gracePeriodMinutes = gracePeriodMinutes;
        this.allowedReceiptExtensions = allowedReceiptExtensions;
        this.maxFileSizeMb = maxFileSizeMb;
    }

    public String getTitle() {
        return title;
    }

    public int getMinimumTierLevel() {
        return minimumTierLevel;
    }

    public List<String> getRequiredCategories() {
        return requiredCategories;
    }

    public Region getRegion() {
        return region;
    }

    public boolean isOpenToAllRegions() {
        return openToAllRegions;
    }

    public boolean isLocked() {
        return locked;
    }

    public LocalDateTime getClaimDeadline() {
        return claimDeadline;
    }

    public int getGracePeriodMinutes() {
        return gracePeriodMinutes;
    }

    public List<String> getAllowedReceiptExtensions() {
        return allowedReceiptExtensions;
    }

    public double getMaxFileSizeMb() {
        return maxFileSizeMb;
    }
}

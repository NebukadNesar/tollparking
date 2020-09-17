package com.example.tollparking.api.policy;

/**
 * Pricing Policy defines the what pricing should be applied to parked cars in slots.
 * A slot or car might be obligated to one or more pricing policy.
 */
public class PricingPolicy {

    /**
     * Specific to policies' own pricing model, added to calculation as it is.
     */
    private double defaultPrice;

    /**
     * generic if required to be used in hourly pricing calculation, if this value is zero than the calculation wont be affected.
     */
    private double hourlyPrice;

    /**
     * describes the policy name
     */
    private String pricingPolicyName;

    public PricingPolicy(String pricingPolicyName, double defaultPrice, double hourlyPrice) {
        this.defaultPrice      = defaultPrice;
        this.hourlyPrice       = hourlyPrice;
        this.pricingPolicyName = pricingPolicyName;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public double getHourlyPrice() {
        return hourlyPrice;
    }

    public void setHourlyPrice(double hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public String getPricingPolicyName() {
        return pricingPolicyName;
    }

    public void setPricingPolicyName(String pricingPolicyName) {
        this.pricingPolicyName = pricingPolicyName;
    }
}

package com.example.tollparking.api.bill;

import com.example.tollparking.api.policy.PolicyManager;
import com.example.tollparking.api.policy.PricingPolicy;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.validation.ValidationUtil;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>Billing manager calculates the total bill of the parked care in a slot when car leaves the slot, also applies the
 * pricing policies of that type of slot to its bill total.
 */
public class BillingManager {

    private PolicyManager policyManager = PolicyManager.getInstance();

    /**
     * Method is used to calculate the final price of the leaving car
     *
     * @param response
     * @throws ParkingException
     */
    public void billCustomer(Response response) throws ParkingException {
        ValidationUtil.validateNotNull(response, "billCustomer response is null");

        Set<PricingPolicy> pricingPolicySet = policyManager.getPolicyListFrom(response.getAppliedPolicies());
        ValidationUtil.validateNotNull(pricingPolicySet, "Currently there is no policy for this slot." + response.getAppliedPolicies());

        double newBill     = response.getPrice();
        long   currentTime = System.currentTimeMillis();
        long   hour        = TimeUnit.MILLISECONDS.toHours(currentTime - response.getParkStartAsMillis());
        for (PricingPolicy policy : pricingPolicySet) {
            newBill += applyPolicyPrices(newBill, policy, hour);
        }
        response.setPricingPolicySet(pricingPolicySet);
        response.setPrice(newBill);
    }

    /**
     * Applies slots' each policy to the bill
     *
     * @param bill
     * @param policy
     * @param hour
     *
     * @return
     */
    private double applyPolicyPrices(double bill, PricingPolicy policy, long hour) {
        double newPrice = bill + policy.getDefaultPrice() + (policy.getHourlyPrice() * hour);
        System.out.println(" bill  +\"-\"+ policy.getDefaultPrice() + (policy.getHourlyPrice() * hour = " +  bill  +"-"+ policy.getDefaultPrice()  +"-"+ (policy.getHourlyPrice()*hour)+"");
        return newPrice;
    }

}

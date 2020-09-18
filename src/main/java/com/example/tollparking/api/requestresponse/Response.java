package com.example.tollparking.api.requestresponse;

import com.example.tollparking.api.policy.PricingPolicy;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>Response class is used to return different type of messages to caller,
 * <p> E.g: a parking might not have free slot in this case api returns a noneEmptySlotResponse
 *
 */
public class Response implements Serializable {

    enum ResponseType {
        FAIL, SUCCESS
    }

    /**
     * status CODE when returning a response to user
     */
    private int status;

    /**
     * atNumber defines vehicle parked position in slots
     */
    private int atNumber;

    /**
     * status desc provides a detailed message to user along with status code
     */
    private String statusDesc;

    /**
     * at what time the park has started
     */
    private long parkStartAsMillis;

    /**
     * price of total parking, calculate with policies
     */
    private double price;

    /**
     * what type of policies are applied, this attribute defines that policies set name
     */
    private String appliedPolicies;

    /**
     * applied policies set to the final total
     */
    private Set<PricingPolicy> pricingPolicySet;

    /**
     * response might be successful or unsuccessful depending on the excpetions, errors or missing slot type, none empty
     * slots etc.. this attribute defines success or fail of response
     */
    private ResponseType responseType;

    public Response() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAtNumber() {
        return atNumber;
    }

    public void setAtNumber(int atNumber) {
        this.atNumber = atNumber;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public long getParkStartAsMillis() {
        return parkStartAsMillis;
    }

    public void setParkStartAsMillis(long parkStartAsMillis) {
        this.parkStartAsMillis = parkStartAsMillis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAppliedPolicies() {
        return appliedPolicies;
    }

    public void setAppliedPolicies(String code) {
        this.appliedPolicies = code;
    }

    public Set<PricingPolicy> getPricingPolicySet() {
        return pricingPolicySet;
    }

    public void setPricingPolicySet(Set<PricingPolicy> pricingPolicySet) {
        this.pricingPolicySet = pricingPolicySet;
    }

    public void succes() {
        responseType = ResponseType.SUCCESS;
    }

    public void fail() {
        responseType = ResponseType.FAIL;
    }

    public boolean isSuccessFull() {
        return ResponseType.SUCCESS.equals(responseType);
    }

}

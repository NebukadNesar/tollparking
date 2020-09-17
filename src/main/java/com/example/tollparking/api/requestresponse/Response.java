package com.example.tollparking.api.requestresponse;

import com.example.tollparking.api.policy.PricingPolicy;

import java.io.Serializable;
import java.util.Set;

public class Response implements Serializable {

    enum ResponseType {
        FAIL, SUCCESS
    }

    private int                status;
    private int                atNumber;
    private String             statusDesc;
    private long               parkStartAsMillis;
    private double             price;
    private String             appliedPolicies;
    private Set<PricingPolicy> pricingPolicySet;
    private ResponseType       responseType;

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

package com.example.tollparking.api.api.parkingslot;

import tollparking.api.policy.PricingPolicy;

public interface ISlot {

    default void applyPolicy(PricingPolicy p) {
        //TODO apply policy
    }

}

package com.example.tollparking.api.api.price;

import tollparking.api.parkingslot.ISlot;
import tollparking.api.policy.PricingPolicy;

import java.util.List;

public interface ICalculate<T extends ISlot> {

    public double calculatePrice();

    default void applyPolicy(PricingPolicy policy, T t) {
        t.applyPolicy(policy);
    }

    public default void applyPolicy(PricingPolicy policy, List<T> t) {
        if (t == null || t.size() == 0) {
            return;
        }
        for (T v : t) {
            applyPolicy(policy, v);
        }
    }

}

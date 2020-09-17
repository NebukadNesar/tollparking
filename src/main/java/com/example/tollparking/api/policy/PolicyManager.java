package com.example.tollparking.api.policy;

import com.example.tollparking.api.constants.ParkingConstants;
import com.example.tollparking.api.validation.ParkingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PolicyManager {

    static final PolicyManager instance = new PolicyManager();

    public static PolicyManager getInstance() {
        return instance;
    }

    private static  Map<String, PricingPolicy> pricingPolicySet  = new HashMap<>();
    private static  Set<PricingPolicy>         sedanSlotPolicies = new HashSet<>();
    private static  Set<PricingPolicy>         ec20WSlotPolicies = new HashSet<>();
    private static  Set<PricingPolicy>         ec50WSlotPolicies = new HashSet<>();

    static {

        try {
            constructPolicies();
        } catch (IOException | ParkingException e) {
            e.printStackTrace();
        }

    }

    private static void constructPolicies() throws IOException, ParkingException {
        PolicyConfigParser policyConfigParser = new PolicyConfigParser();
        policyConfigParser.constructPolicyFromProperty();
        sedanSlotPolicies = policyConfigParser.getSedanSlotPolices();
        ec20WSlotPolicies = policyConfigParser.getEc20WattSlotPolices();
        ec50WSlotPolicies = policyConfigParser.getEc50WattSlotPolices();
        pricingPolicySet = policyConfigParser.getPricingPolicySet();
    }


    public Set<PricingPolicy> getPolicyListFrom(String slotType) {
        if (ParkingConstants.SEDAN.equals(slotType)) {
            return sedanSlotPolicies;
        }
        if (ParkingConstants.EC20WATT.equals(slotType)) {
            return ec20WSlotPolicies;
        }

        if (ParkingConstants.EC50WATT.equals(slotType)) {
            return ec50WSlotPolicies;
        }
        return null;
    }

}

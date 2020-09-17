package com.example.tollparking.api.policy;

import com.example.tollparking.api.constants.ParkingConstants;
import com.example.tollparking.api.validation.ParkingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>PolicyConfigManager does two things
 * <p>1 -calls @PolicyConfigParser class to read config-properties.json file
 * <p>2 -returns the constructed policies of a type when requested
 */
public class PolicyManager {

    static final PolicyManager instance = new PolicyManager();

    public static PolicyManager getInstance() {
        return instance;
    }


    private static Map<String, PricingPolicy> pricingPolicySet  = new HashMap<>();
    private static Set<PricingPolicy>         sedanSlotPolicies = new HashSet<>();
    private static Set<PricingPolicy>         ec20WSlotPolicies = new HashSet<>();
    private static Set<PricingPolicy>         ec50WSlotPolicies = new HashSet<>();

    static {

        try {
            constructPolicies();
        } catch (IOException | ParkingException e) {
            e.printStackTrace();
        }

    }

    /**
     * (non-Javadoc)
     *
     * @throws IOException
     * @throws ParkingException
     */
    private static void constructPolicies() throws IOException, ParkingException {
        PolicyConfigParser policyConfigParser = new PolicyConfigParser();
        policyConfigParser.constructPolicyFromProperty();
        sedanSlotPolicies = policyConfigParser.getSedanSlotPolices();
        ec20WSlotPolicies = policyConfigParser.getEc20WattSlotPolices();
        ec50WSlotPolicies = policyConfigParser.getEc50WattSlotPolices();
        pricingPolicySet  = policyConfigParser.getPricingPolicySet();
    }


    /**
     * Method returns policies of a type
     *
     * @param slotType
     *
     * @return
     */
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

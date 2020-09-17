package com.example.tollparking.api.policy;

import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.validation.ValidationUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PolicyConfigParser {

    private static final Map<String, PricingPolicy> pricingPolicySet  = new HashMap<>();
    private static final Set<PricingPolicy>         sedanSlotPolicies = new HashSet<>();
    private static final Set<PricingPolicy>         ec20WSlotPolicies = new HashSet<>();
    private static final Set<PricingPolicy>         ec50WSlotPolicies = new HashSet<>();

    public static void constructPolicyFromProperty() throws IOException, ParkingException {

        Reader      reader      = new FileReader("config-properties.json");
        JsonElement jsonElement = JsonParser.parseReader(reader);

        JsonObject jsonObject    = jsonElement.getAsJsonObject();
        JsonArray  policiesArray = jsonObject.getAsJsonArray("policy-properties");
        ValidationUtil.validateNotNull(policiesArray, "Policy array is null.");

        constructPolicies(policiesArray);

        JsonObject slotPolicies = jsonObject.getAsJsonObject("slot-policies");
        JsonArray  sedan        = slotPolicies.getAsJsonArray("sedan-policies");
        JsonArray  ec20w        = slotPolicies.getAsJsonArray("ec20w-policies");
        JsonArray  ec50w        = slotPolicies.getAsJsonArray("ec50w-policies");

        constructSedanSlotPolicies(sedan);
        construct20WattSlotPolicies(ec20w);
        construct50WattSlotPolicies(ec50w);

    }

    private static void construct50WattSlotPolicies(JsonArray ec50WattPolicies) throws ParkingException {
        ValidationUtil.validateNotNull(ec50WattPolicies, "EC50Watt car policy is empty or null.");
        for (JsonElement el : ec50WattPolicies) {
            String policyName = el.getAsString();
            if (policyName.isEmpty() || !pricingPolicySet.containsKey(policyName)) {
                throw new ParkingException("Policy definition is wrong or there is not such policy : " + policyName);
            }
            ec50WSlotPolicies.add(pricingPolicySet.get(policyName));
        }
        if (ec50WSlotPolicies.size() == 0) {
            throw new ParkingException("There is no EC50Watt slot policies defined in config file.");
        }
    }

    private static void construct20WattSlotPolicies(JsonArray ec20WattPolicies) throws ParkingException {
        ValidationUtil.validateNotNull(ec20WattPolicies, "EC20Watt car policy is empty or null.");
        for (JsonElement el : ec20WattPolicies) {
            String policyName = el.getAsString();
            if (policyName.isEmpty() || !pricingPolicySet.containsKey(policyName)) {
                throw new ParkingException("Policy definition is wrong or there is not such policy : " + policyName);
            }
            ec20WSlotPolicies.add(pricingPolicySet.get(policyName));
        }
        if (ec20WSlotPolicies.size() == 0) {
            throw new ParkingException("There is no EC20Watt slot policies defined in config file.");
        }
    }

    private static void constructSedanSlotPolicies(JsonArray sedanPolicies) throws ParkingException {
        ValidationUtil.validateNotNull(sedanPolicies, "Sedan car policy is empty or null.");
        for (JsonElement el : sedanPolicies) {
            String policyName = el.getAsString();
            if (policyName.isEmpty() || !pricingPolicySet.containsKey(policyName)) {
                throw new ParkingException("Policy definition is wrong or there is not such policy : " + policyName);
            }
            sedanSlotPolicies.add(pricingPolicySet.get(policyName));
        }
        if (sedanSlotPolicies.size() == 0) {
            throw new ParkingException("There is no Sedan slot policies defined in config file.");
        }

    }

    private static void constructPolicies(JsonArray pricingPolicies) throws ParkingException {

        /**
         * policy oluşturmada az bir yer kaldı onu da tamamlayınca işler temizlenir. Json okuma yaptık simdik okudugumzu
         * objecleri oluşturma zamanı
         */
        for (JsonElement el : pricingPolicies) {
            /**
             *   "name": "EC20",
             *   "default-price": 3,
             *   "hourly-price": 2
             */
            JsonObject  jsonObject           = el.getAsJsonObject();
            JsonElement policyName           = jsonObject.get("name");
            JsonElement defaultPriceOfPolicy = jsonObject.get("default-price");
            JsonElement hourlyPriceOfPolicy  = jsonObject.get("hourly-price");

            ValidationUtil.validateNotNull(policyName, "Policy name is null");
            ValidationUtil.validateNotNull(defaultPriceOfPolicy, "Policy default price is null");
            ValidationUtil.validateNotNull(hourlyPriceOfPolicy, "Policy hourly price is null");

            String name         = policyName.getAsString();
            double defaultPrice = Math.abs(defaultPriceOfPolicy.getAsDouble());
            double hourlyPrice  = Math.abs(hourlyPriceOfPolicy.getAsDouble());

            PricingPolicy pricingPolicy = new PricingPolicy(name, defaultPrice, hourlyPrice);

            pricingPolicySet.put(name, pricingPolicy);
        }
    }

    public Map getPricingPolicySet() {
        return pricingPolicySet;
    }

    public Set getSedanSlotPolices() {
        return sedanSlotPolicies;
    }

    public Set getEc20WattSlotPolices() {
        return ec20WSlotPolicies;
    }

    public Set getEc50WattSlotPolices() {
        return ec50WSlotPolicies;
    }

}

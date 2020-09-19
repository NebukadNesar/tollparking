package com.example.tollparking.api.slot;

import com.example.tollparking.api.constants.ParkingConstants;

/**
 * SlotType defines the discrite slots for each kind of vehicles with unique slot code, a description of slot and max
 * available slots per type.
 */
public enum SlotType {

    SEDAN(ParkingConstants.SEDAN, "Sedan Cars", 50), EC20WATT(ParkingConstants.EC20WATT, "Electrical Cars 20 WATT", 30), EC50WATT(ParkingConstants.EC50WATT, "Electrical Cars 50 WATT", 40), NONE("", "", 0);

    private String code;
    private String slotName;
    private int    maxSlot;

    SlotType(String code, String slotName, int maxSlot) {
        this.code     = code;
        this.slotName = slotName;
        this.maxSlot  = maxSlot;
    }

    public static SlotType get(String type) {
        if (SEDAN.code.equals(type))
            return SEDAN;
        if (EC20WATT.code.equals(type))
            return EC20WATT;
        if (EC50WATT.code.equals(type))
            return EC50WATT;
        return NONE;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public int getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }

}

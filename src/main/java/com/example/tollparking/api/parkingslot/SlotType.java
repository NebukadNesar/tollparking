package com.example.tollparking.api.parkingslot;

/**
 * SlotType defines the discrite slots for each kind of vehicles with unique slot code, a description of slot and max
 * available slots per vehicle type.
 */
public enum SlotType {
    SEDAN("SEDAN", "Sedan Cars", 50), EC20WATT("EC20", "Electrical Cars 20 WATT", 30), EC50WATT("EC50", "Electrical Cars 50 WATT", 40), NONE("", "", 0);

    String code;
    String slotName;
    int    maxSlot;

    SlotType(String code, String slotName, int maxSlot) {
        this.code     = code;
        this.slotName = slotName;
        this.maxSlot  = maxSlot;
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

package com.example.tollparking.api.slot;

import java.util.List;

import static com.example.tollparking.api.constants.ParkingConstants.*;

/**
 * Construct slots of given type and given number
 */
public class StaticSlotFactory {

    public static final List<Slot> sedanSlot;
    public static final List<Slot> ec20WattSlot;
    public static final List<Slot> ec50WattSlot;

    /*
     * initialize slots
     */
    static {
        sedanSlot    = constructSedanSlots();
        ec20WattSlot = constructEC20WATTSlots();
        ec50WattSlot = constructEC50WATTSlots();
    }

    private static List constructSedanSlots() {
        return new SedanSlotFactory(SEDAN_SLOT_COUNT).construct();
    }

    private static List constructEC20WATTSlots() {
        return new EC20WattSlotFactory(EC20W_SLOT_COUNT).construct();
    }

    private static List constructEC50WATTSlots() {
        return new EC20WattSlotFactory(EC50W_SLOT_COUNT).construct();
    }

}

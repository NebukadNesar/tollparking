package com.example.tollparking.api.slot;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract Slot factory class
 */
public abstract class AbstractSlotFactory {

    public abstract List construct();

    /**
     * Method create a slot list with the given type and given slotCount. Constructed list are not extendable no more
     * elements can be added.
     *
     * @param type
     * @param slotCount
     *
     * @return
     */
    List construct(SlotType type, int slotCount) {
        List<Slot> slots = Arrays.asList(new Slot[slotCount]);
        for (int i = 0; i < slotCount; i++) {
            Slot slot = new Slot();
            slot.setStatus(Slot.SlotStatus.EMPTY);
            slot.setStartTime(0l);
            slot.setSlotType(type);
            slot.setSlotNumber(i);
            slots.set(i, slot);
        }
        return slots;
    }

}

package com.example.tollparking.api.slot;

import java.util.List;

/**
 * EC50KWatt Slot factory class
 */
public class EC50WattSlotFactory extends AbstractSlotFactory {

    private int capacity;

    public EC50WattSlotFactory(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public List construct() {
        return super.construct(SlotType.EC50WATT, capacity);
    }

}

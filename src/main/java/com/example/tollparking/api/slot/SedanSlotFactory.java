package com.example.tollparking.api.slot;

import java.util.List;

/**
 * Sedan Slot factory class
 */
public class SedanSlotFactory extends AbstractSlotFactory {

    private int capacity;

    public SedanSlotFactory(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public List construct() {
        return super.construct(SlotType.SEDAN, capacity);
    }

}

package com.example.tollparking.api.slot;

import java.util.List;

public class EC20WattSlotFactory extends AbstractSlotFactory {

    private int capacity;

    public EC20WattSlotFactory(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public List construct() {
        return super.construct(SlotType.EC20WATT, capacity);
    }

}

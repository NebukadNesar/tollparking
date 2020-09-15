package com.example.tollparking.api.api.parkingslot;

/**
 * Slot defines each parkable area with type, slot park start time and status that describes the empty or full slot.
 * When slot becomes empty start time reset.
 */
public class Slot implements ISlot{

    private enum SlotStatus {
        EMPTY("E"), FULL("F");

        private String status;

        SlotStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        static SlotStatus getSlotStatus(String status) {
            if ("E".equals(status))
                return EMPTY;
            if ("F".equals(status))
                return FULL;
            return null;
        }
    }

    private SlotType slotType;
    private long     startTime;
    private String   status;

    public Slot() {
    }

    public Slot(SlotType slotType, long startTime, String status) {
        this.slotType  = slotType;
        this.startTime = startTime;
        this.status    = status;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

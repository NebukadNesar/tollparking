package com.example.tollparking.api.slot;

import com.example.tollparking.api.vehicle.IVehicle;

/**
 * Slot defines each parkable area with type, slot park start time and status that describes the empty or full slot.
 * When slot becomes empty start time reset.
 */
public class Slot {

    public enum SlotStatus {
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

    private SlotType   slotType;
    private long       startTime;
    private SlotStatus status;
    private IVehicle   vehicleInSlot;
    private int        slotNumber;

    public Slot() {
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

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public IVehicle getVehicleInSlot() {
        return vehicleInSlot;
    }

    public void setVehicleInSlot(IVehicle vehicleInSlot) {
        this.vehicleInSlot = vehicleInSlot;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public void free() {
        this.startTime     = 0;
        this.vehicleInSlot = null;
        this.status        = SlotStatus.EMPTY;
    }

}

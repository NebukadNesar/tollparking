package com.example.tollparking.api.slot;

import com.example.tollparking.api.vehicle.IVehicle;

/**
 * Slot defines each park-able area of a type, an empty slot startTime is reset to 0.
 */
public class Slot {

    /**
     * Enum defines the status of slot, EMPTY means it can accept new vehicles, FULL means it cannot accept new vehicles
     */
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

    /**
     * slotType defines what type of slot is this
     */
    private SlotType slotType;

    /**
     * startTime at what time slot started being used by a vehicle
     */
    private long startTime;

    /**
     * parameter defines EMPTY or FULL status of slot, when car parked in a slot, status set to FULL, otherwise EMPTY
     */
    private SlotStatus status;

    /**
     * sets which car is in slot
     */
    private IVehicle vehicleInSlot;

    /**
     * slotNumber defines position of slot in slot list, also shows user what number his/her car is parked
     */
    private int slotNumber;

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

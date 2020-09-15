package com.example.tollparking.api.api.park;

import tollparking.api.parkingslot.SlotType;
import tollparking.api.validation.ParkingException;
import tollparking.api.validation.ValidationUtil;
import tollparking.api.vehicle.Vehicle;

public interface IParkVehicle {


    Response accept(Request request) throws ParkingException;


    default SlotType findSlotType(Vehicle vehicle) throws ParkingException {
        ValidationUtil.validateNotNull(vehicle, "Vehicle in request cannot be null.");
        SlotType slotType = null;

        return slotType;
    }
}

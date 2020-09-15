package com.example.tollparking.api.api.park;

import tollparking.api.parkingslot.SlotType;
import tollparking.api.validation.ParkingException;

public class Park implements IParkVehicle {

    public Response accept(Request request) throws ParkingException {


        Response response = new Response();
        SlotType slotType = findSlotType(request.getVehicle());


        return response;
    }

}

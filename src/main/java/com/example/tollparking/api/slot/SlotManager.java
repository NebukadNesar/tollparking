package com.example.tollparking.api.slot;

import com.example.tollparking.api.bill.BillingManager;
import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.requestresponse.ResponseManager;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.vehicle.IVehicle;

import java.util.List;

import static com.example.tollparking.api.slot.Slot.SlotStatus.EMPTY;
import static com.example.tollparking.api.slot.Slot.SlotStatus.FULL;
import static com.example.tollparking.api.slot.StaticSlotFactory.*;

/**
 * SlotManager is the one doing all the management work,
 * <p>1-finding an empty slot of that type
 * <p>2-returning a proper  response message to caller
 * <p>3-billing customer
 * <p>4-freeing slot
 */
public class SlotManager {

    private static final SlotManager     instance        = new SlotManager();
    private              ResponseManager responseManager = new ResponseManager();
    private              BillingManager  billingManager  = new BillingManager();

    public static SlotManager getInstance() {
        return instance;
    }

    /**
     * From request object, vehicle is get and with vehicle type relative slot type is found and searches for free slot
     * <p> if there is not slot in that type, a noEmptySlot messages returned.
     *
     * @param request
     *
     * @return
     *
     * @throws ParkingException
     */
    public synchronized Response parkVehicle(Request request) throws ParkingException {
        IVehicle vehicle   = request.getVehicle();
        Slot     emptySlot = findEmptySlot(vehicle);

        if (emptySlot == null) {
            return responseManager.constructNoneEmptySlotResponse(vehicle);
        }
        Slot parkedSlot = park(emptySlot, vehicle);
        return responseManager.constructSuccessResponse(parkedSlot);
    }

    /**
     * Method unparks a requested vehicle type
     *
     * @param request
     * @return
     * @throws ParkingException
     */
    public synchronized Response unParkVehicle(Request request) throws ParkingException {
        IVehicle vehicle  = request.getVehicle();
        SlotType slotType = SlotType.get(vehicle.getType());
        if(SlotType.NONE.equals(slotType)){
            return responseManager.constructNoSuchSlotTypeResponse(vehicle.getType());
        }
        Response response = findVehicleAndRemove(slotType);
        if (!response.isSuccessFull()) {
            return response;
        }
        billingManager.billCustomer(response);
        return response;
    }

    /**
     * Method finds one vehicle in that slot type and removes it
     *
     * @param slotType
     * @return
     */
    private Response findVehicleAndRemove(SlotType slotType) {
        List<Slot> slotList = from(slotType);
        if (slotList == null) {
            return responseManager.constructNoSuchSlotAvailableResponse(slotType.getCode());
        }
        for (int i = 0; i < slotList.size(); i++) {
            Slot slot = slotList.get(i);
            if (FULL.equals(slot.getStatus())) {
                Response response = responseManager.constructSuccessUnParkResponse(i, slot);
                slot.free();
                return response;
            }
        }
        return responseManager.constructNoCarInSlotUnParkResponse();
    }

    /**
     * Method returns slot list of that type
     *
     * @param slotType
     *
     * @return
     */
    private List<Slot> from(SlotType slotType) {
        if (SlotType.SEDAN.equals(slotType))
            return sedanSlot;
        if (SlotType.EC20WATT.equals(slotType))
            return ec20WattSlot;
        if (SlotType.EC20WATT.equals(slotType))
            return ec50WattSlot;
        return null;
    }

    /**
     * Sets current slot status to FULL and assign that slot the the vehicle
     *
     * @param emptySlot
     * @param vehicle
     *
     * @return
     */
    private Slot park(Slot emptySlot, IVehicle vehicle) {
        emptySlot.setStartTime(System.currentTimeMillis());
        emptySlot.setVehicleInSlot(vehicle);
        emptySlot.setStatus(FULL);
        return emptySlot;
    }

    /**
     * Finds empty slot for vehicle type
     *
     * @param vehicle
     *
     * @return
     */
    public Slot findEmptySlot(IVehicle vehicle) {
        SlotType slotType = SlotType.valueOf(vehicle.getType());
        if (SlotType.SEDAN.equals(slotType))
            return sedanSlot();
        if (SlotType.EC20WATT.equals(slotType))
            return ec20WSlot();
        if (SlotType.EC20WATT.equals(slotType))
            return ec50WSlot();
        return null;
    }

    private Slot ec50WSlot() {
        for (Slot s : ec50WattSlot) {
            if (EMPTY.equals(s.getStatus())) {
                return s;
            }
        }
        return null;
    }

    private Slot ec20WSlot() {
        for (Slot s : ec20WattSlot) {
            if (EMPTY.equals(s.getStatus())) {
                return s;
            }
        }
        return null;
    }

    private Slot sedanSlot() {
        for (Slot s : sedanSlot) {
            if (EMPTY.equals(s.getStatus())) {
                return s;
            }
        }
        return null;
    }
}

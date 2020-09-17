package com.example.tollparking.api.slot;

import com.example.tollparking.api.bill.BillingManager;
import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.requestresponse.ResponseManager;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.validation.ValidationUtil;
import com.example.tollparking.api.vehicle.IVehicle;

import java.util.List;

import static com.example.tollparking.api.slot.Slot.SlotStatus.EMPTY;
import static com.example.tollparking.api.slot.Slot.SlotStatus.FULL;
import static com.example.tollparking.api.slot.StaticSlotFactory.*;

public class SlotManager {

    private static final SlotManager     instance        = new SlotManager();
    private              ResponseManager responseManager = new ResponseManager();
    private              BillingManager  billingManager  = new BillingManager();

    public static SlotManager getInstance() {
        return instance;
    }

    public synchronized Response parkVehicle(Request request) throws ParkingException {
        IVehicle vehicle   = request.getVehicle();
        Slot     emptySlot = findEmptySlot(vehicle);

        if (emptySlot == null) {
            return responseManager.constructNoneEmptySlotResponse(vehicle);
        }
        Slot parkedSlot = park(emptySlot, vehicle);
        return responseManager.constructSuccessResponse(parkedSlot);
    }

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

    private List<Slot> from(SlotType slotType) {
        if (SlotType.SEDAN.equals(slotType))
            return sedanSlot;
        if (SlotType.EC20WATT.equals(slotType))
            return ec20WattSlot;
        if (SlotType.EC20WATT.equals(slotType))
            return ec50WattSlot;
        return null;
    }

    private Slot park(Slot emptySlot, IVehicle vehicle) {
        emptySlot.setStartTime(System.currentTimeMillis());
        emptySlot.setVehicleInSlot(vehicle);
        emptySlot.setStatus(FULL);
        return emptySlot;
    }

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

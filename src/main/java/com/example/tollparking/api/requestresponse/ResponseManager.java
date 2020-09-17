package com.example.tollparking.api.requestresponse;

import com.example.tollparking.api.slot.Slot;
import com.example.tollparking.api.vehicle.IVehicle;

import static com.example.tollparking.api.requestresponse.ResponseCodes.*;

/**
 * Response Manager consructs the generic successful or unsuccssful response messages
 */
public class ResponseManager {

    /**
     * parked successfully response
     *
     * @param slot
     *
     * @return
     */
    public Response constructSuccessResponse(Slot slot) {
        Response succesResponse = new Response();
        succesResponse.setParkStartAsMillis(slot.getStartTime());
        succesResponse.setStatus(PARK_SUCCESS_CODE);
        succesResponse.setStatusDesc(PARK_SUCCESS_CODE_DESC);
        succesResponse.setAtNumber(slot.getSlotNumber());
        succesResponse.succes();
        return succesResponse;
    }

    /**
     * unparked successfully response
     *
     * @param fromNumber
     * @param slot
     *
     * @return
     */
    public Response constructSuccessUnParkResponse(int fromNumber, Slot slot) {
        Response successUnParkResponse = new Response();
        successUnParkResponse.setParkStartAsMillis(slot.getStartTime());
        successUnParkResponse.setPrice(0l);
        successUnParkResponse.setAtNumber(fromNumber);
        successUnParkResponse.setStatusDesc(UNPARK_SUCCESS_CODE_DESC);
        successUnParkResponse.setStatus(UNPARK_SUCCESS_CODE);
        successUnParkResponse.setAppliedPolicies(slot.getSlotType().getCode());
        successUnParkResponse.succes();
        return successUnParkResponse;
    }

    /**
     * requested slot type is not available response
     *
     * @param slotType
     *
     * @return
     */
    public Response constructNoSuchSlotTypeResponse(String slotType) {
        Response noSuchSlotResponse = new Response();
        noSuchSlotResponse.setParkStartAsMillis(0l);
        noSuchSlotResponse.setStatus(NO_SUCH_SLOT);
        noSuchSlotResponse.setStatusDesc(String.format(NO_SUCH_SLOT_DESC, slotType));
        noSuchSlotResponse.setPrice(0);
        noSuchSlotResponse.fail();
        return noSuchSlotResponse;
    }

    /**
     * Even thought the slot type exist such slot might be exists in park.
     *
     * @param slotType
     *
     * @return
     */
    public Response constructNoSuchSlotAvailableResponse(String slotType) {
        Response noSuchSlotResponse = new Response();
        noSuchSlotResponse.setParkStartAsMillis(0l);
        noSuchSlotResponse.setStatus(NO_SUCH_SLOT_AVAIL);
        noSuchSlotResponse.setStatusDesc(String.format(NO_SUCH_SLOT_AVAIL_DESC, slotType));
        noSuchSlotResponse.setPrice(0);
        noSuchSlotResponse.fail();
        return noSuchSlotResponse;
    }

    /**
     * there is no free slot left in the requested slot response
     *
     * @param vehicle
     *
     * @return
     */
    public Response constructNoneEmptySlotResponse(IVehicle vehicle) {
        Response noneEmptySlotResponse = new Response();
        noneEmptySlotResponse.setParkStartAsMillis(0l);
        noneEmptySlotResponse.setStatus(NONE_EMPTY_SLOT);
        noneEmptySlotResponse.setStatusDesc(String.format(NONE_EMPTY_SLOT_DESC, vehicle.getType()));
        noneEmptySlotResponse.setPrice(0);
        noneEmptySlotResponse.fail();
        return noneEmptySlotResponse;
    }

    /**
     * there is no car to unpark in the requested slot response
     *
     * @return
     */
    public Response constructNoCarInSlotUnParkResponse() {
        Response noCarInParkResponse = new Response();
        noCarInParkResponse.setPrice(0l);
        noCarInParkResponse.setStatus(NO_CAR_INSLOT);
        noCarInParkResponse.setStatusDesc(NO_CAR_INSLOT_DESC);
        noCarInParkResponse.fail();
        return noCarInParkResponse;
    }


}

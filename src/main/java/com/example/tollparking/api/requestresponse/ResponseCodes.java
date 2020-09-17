package com.example.tollparking.api.requestresponse;

/**
 * ResponseCodes and ResponseCodeDesc are used in response object to define current situation of the api call
 */
public interface ResponseCodes {

    int    NONE_EMPTY_SLOT      = 33;
    String NONE_EMPTY_SLOT_DESC = "There is no empty slot in {0} area";

    int    NO_SUCH_SLOT      = 34;
    String NO_SUCH_SLOT_DESC = "There is no such slot {0} type";

    int    PARK_SUCCESS_CODE      = 11;
    String PARK_SUCCESS_CODE_DESC = "Car parked successfully";

    int    UNPARK_SUCCESS_CODE      = 12;
    String UNPARK_SUCCESS_CODE_DESC = "Car unparked successfully";

    int    NO_CAR_INSLOT      = 13;
    String NO_CAR_INSLOT_DESC = "There is no car parked in this slots.";

    int    NO_SUCH_SLOT_AVAIL      = 14;
    String NO_SUCH_SLOT_AVAIL_DESC = "There is no such slot availble";

}

package com.example.tollparking.api.requestresponse;

/**
 * <p>A custom return/status codes and their descriptions
 *
 * <p>ResponseCodes and ResponseCodeDesc are used in response object to define current situation of the api call
 */
public interface ResponseCodes {

    /**
     * for the requested slot type, there is no empty slot currently
     */
    int    NONE_EMPTY_SLOT      = 33;
    String NONE_EMPTY_SLOT_DESC = "There is no empty slot currently";

    /**
     * requested slot is not exist in slot manager
     */
    int    NO_SUCH_SLOT      = 34;
    String NO_SUCH_SLOT_DESC = "There is no such slot {0} type";

    /**
     * park completed successfully, a relative response message will be generated with this code & desc
     */
    int    PARK_SUCCESS_CODE      = 11;
    String PARK_SUCCESS_CODE_DESC = "Car parked successfully";

    /**
     * unpark completed successfully, a relative response message will be generated with this code & desc
     */
    int    UNPARK_SUCCESS_CODE      = 12;
    String UNPARK_SUCCESS_CODE_DESC = "Car unparked successfully";

    /**
     * for <strong>unpark</strong> request there is no car in that slot type
     */
    int    NO_CAR_INSLOT      = 13;
    String NO_CAR_INSLOT_DESC = "There is no car parked in this slots.";

    /**
     * for the requested slot type, there is no such slot exist to park a car or unpark a car
     */
    int    NO_SUCH_SLOT_AVAIL      = 14;
    String NO_SUCH_SLOT_AVAIL_DESC = "There is no such slot available";

}

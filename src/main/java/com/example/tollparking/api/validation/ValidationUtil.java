package com.example.tollparking.api.validation;

import static com.example.tollparking.api.validation.IValidationConstants.NULL_OBJ_MESSAGE;

/**
 * ValidationUtil is used to validate request response objects with a custom message send to it.
 */
public class ValidationUtil {

    public static void validateNotNull(Object o, String customMessage) throws ParkingException {
        if (o != null) {
            return;
        }

        if (customMessage != null && !customMessage.isEmpty())
            throw new ParkingException(customMessage);
        else
            throw new ParkingException(NULL_OBJ_MESSAGE);
    }

}

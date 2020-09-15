package com.example.tollparking.api.api.validation;

import static tollparking.api.validation.IValidationConstants.NULL_OBJ_MESSAGE;

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

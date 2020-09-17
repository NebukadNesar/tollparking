package com.example.tollparking.api.validation;

/**
 * Exception specialized for parking
 */
public class ParkingException extends Throwable {

    public ParkingException() {
    }

    public ParkingException(String message) {
        super(message);
    }

    public ParkingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkingException(Throwable cause) {
        super(cause);
    }

    public ParkingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

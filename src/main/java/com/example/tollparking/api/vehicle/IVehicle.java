package com.example.tollparking.api.vehicle;

/**
 * Group the cars
 */
public interface IVehicle {

    public String getType();

    /*
     * When constructing a care with a type user can enter different value for sedan car
     * So this will prevent entering a wrong care into wrong slot.
     */
    default boolean autoValidateCarType(String type) {
        if (!type.equals(getType()))
            return false;

        return true;
    }
}


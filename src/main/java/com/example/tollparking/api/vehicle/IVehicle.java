package com.example.tollparking.api.vehicle;


/**
 * Vehicle interface can be used to extend the vehicle type if there were different properties defined. However only the
 * type of the vehicle is provided.
 */
public interface IVehicle {

    /**
     * Method returns the type of the vehicle so that slot type can be find
     *
     * @return
     */
    public String getType();
}


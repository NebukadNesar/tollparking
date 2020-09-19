package com.example.tollparking.api.vehicle;

/**
 * A simple vehicle definition varying with a type parameter
 */
public class Vehicle implements IVehicle{

    String type;

    public Vehicle() {
    }

    public Vehicle(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

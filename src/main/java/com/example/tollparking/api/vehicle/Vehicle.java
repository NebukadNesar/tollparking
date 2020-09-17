package com.example.tollparking.api.vehicle;

public class Vehicle implements IVehicle{

    String type;

    public Vehicle() {
        super();
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

package com.example.tollparking.api.requestresponse;


import com.example.tollparking.api.vehicle.Vehicle;

import java.io.Serializable;

public class Request implements Serializable {

    private Vehicle vehicle;

    public Request() {
    }

    public Request(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

package com.example.tollparking.api.requestresponse;


import com.example.tollparking.api.vehicle.Vehicle;

import java.io.Serializable;

/**
 * Request class is used to make api call, and contains message body for both Parking and Unparking
 *
 */
public class Request implements Serializable {

    /**
     * vehicle for request
     */
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

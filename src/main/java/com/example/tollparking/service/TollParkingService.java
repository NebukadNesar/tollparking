package com.example.tollparking.service;

import com.example.tollparking.api.park.ParkManager;
import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.validation.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class TollParkingService {

    private ParkManager parkManager = ParkManager.getInstance();

    public Response park(Request request) throws ParkingException {
        ValidationUtil.validateNotNull(request, "Request is null.");
        ValidationUtil.validateNotNull(request.getVehicle(), "Vehicle is null");
        Response response = parkManager.park(request);
        return response;
    }

    public Response unPark(Request request) throws ParkingException {
        ValidationUtil.validateNotNull(request, "Request is null.");
        ValidationUtil.validateNotNull(request.getVehicle(), "slot type is null");
        return parkManager.unPark(request);
    }

    public StatisticsResponse stats() throws ParkingException {
        return parkManager.stats();
    }

}

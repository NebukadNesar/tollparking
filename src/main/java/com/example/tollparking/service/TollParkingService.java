package com.example.tollparking.service;

import com.example.tollparking.api.park.ParkingService;
import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.validation.ValidationUtil;
import org.springframework.stereotype.Service;

/**
 * This class is spring based class and used for easy of use and run
 * <p> validate the requests and calls parking service classes
 */
@Service
public class TollParkingService {

    private ParkingService parkingService = ParkingService.getInstance();

    public Response park(Request request) throws ParkingException {
        ValidationUtil.validateNotNull(request, "Request is null.");
        ValidationUtil.validateNotNull(request.getVehicle(), "Vehicle is null");
        Response response = parkingService.park(request);
        return response;
    }

    public Response unPark(Request request) throws ParkingException {
        ValidationUtil.validateNotNull(request, "Request is null.");
        ValidationUtil.validateNotNull(request.getVehicle(), "slot type is null");
        return parkingService.unPark(request);
    }

    public StatisticsResponse stats() throws ParkingException {
        return parkingService.stats();
    }

}

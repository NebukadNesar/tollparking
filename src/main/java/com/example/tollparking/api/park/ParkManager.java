package com.example.tollparking.api.park;


import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.slot.SlotManager;
import com.example.tollparking.api.statistic.StatisticController;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;

public class ParkManager implements IParkVehicle {

    private static final ParkManager         instance            = new ParkManager();
    private              SlotManager         slotManager         = SlotManager.getInstance();
    private              StatisticController statisticController = new StatisticController();

    public static ParkManager getInstance() {
        return instance;
    }

    public Response park(Request request) throws ParkingException {
        return slotManager.parkVehicle(request);
    }

    public Response unPark(Request request) throws ParkingException {
        return slotManager.unParkVehicle(request);
    }

    public StatisticsResponse stats() {
        return statisticController.getStats();
    }

}

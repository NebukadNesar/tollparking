package com.example.tollparking.api.park;


import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.slot.SlotManager;
import com.example.tollparking.api.statistic.StatisticController;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.validation.ValidationUtil;

/**
 * <p>Parking service class is the public access point of api, a request is handled inside this @ParkingService class
 * and returns a relative response
 */
public class ParkingService implements IParkVehicle {

    private static final ParkingService      instance            = new ParkingService();
    private              SlotManager         slotManager         = SlotManager.getInstance();
    private              StatisticController statisticController = new StatisticController();

    public static ParkingService getInstance() {
        return instance;
    }

    /**
     * Parks a requested vehicle if there are slots free.
     *
     * @param request
     *
     * @return
     *
     * @throws ParkingException
     */
    public Response park(Request request) throws ParkingException {
        ValidationUtil.validateNotNull(request, "Request is null.");
        ValidationUtil.validateNotNull(request.getVehicle(), "Vehicle is null");
        return slotManager.parkVehicle(request);
    }

    /**
     * Unparks a vehicle from the requested slot list
     *
     * @param request
     *
     * @return
     *
     * @throws ParkingException
     */
    public Response unPark(Request request) throws ParkingException {
        ValidationUtil.validateNotNull(request, "Request is null.");
        ValidationUtil.validateNotNull(request.getVehicle(), "Vehicle is null");
        return slotManager.unParkVehicle(request);
    }

    /**
     * Returns the all the slot types information, EMPTY, FULL, ParkStartTime etc..
     *
     * @return
     */
    public StatisticsResponse stats() {
        return statisticController.getStats();
    }

}

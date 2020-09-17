package com.example.tollparking.api.park;

import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;

/**
 * IParkVehicle defines two parking methods <strong>'park'</strong> & <strong>'unpark'</strong>
 */
public interface IParkVehicle {

    /**
     * <p><strong>park</strong> method accepts a request object, of type vehicle requested it tries to
     * find an empty slot in that type of slot list, after finding that free slot, slots properties are set to FULL and
     * assigned to the requested vehicle.
     *
     * @param request
     *
     * @return
     *
     * @throws ParkingException
     */
    Response park(Request request) throws ParkingException;

    /**
     * <p><strong>unpark</strong> method accepts a request object, of type vehicle requested it tries to find FULL slot
     * and frees slot and bill customer with slot spesific policies which are defined in config-properties.json file and
     * returns a proper response with these information.
     *
     * @param request
     *
     * @return
     *
     * @throws ParkingException
     */
    Response unPark(Request request) throws ParkingException;


    /**
     * Method retues and statistical data of parking area, which slots are full which one is empty etc..
     *
     * @return
     */
    StatisticsResponse stats();

}

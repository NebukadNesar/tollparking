package com.example.tollparking.api.park;

import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;


public interface IParkVehicle {

    Response park(Request request) throws ParkingException;

    Response unPark(Request request) throws ParkingException;

    StatisticsResponse stats();

}

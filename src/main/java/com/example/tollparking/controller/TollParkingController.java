package com.example.tollparking.controller;

import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.service.TollParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tollparkingmanager")
public class TollParkingController {

    @Autowired
    TollParkingService tollParkingService;

    @PostMapping("/park")
    public Response park(@RequestBody Request request) throws ParkingException {
        return tollParkingService.park(request);
    }

    @PostMapping("/unpark")
    public Response unpark(@RequestBody Request request) throws ParkingException {
        return tollParkingService.unPark(request);
    }

    @GetMapping(value = "/statistics",  produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsResponse statictics() throws ParkingException {
        return tollParkingService.stats();
    }

}

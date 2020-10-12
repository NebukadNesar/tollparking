package com.example.tollparking.controller;

import com.example.tollparking.api.validation.ParkingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @GetMapping(value = "/")
    public String hi() {
        return "hi.";
    }
}

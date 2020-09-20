package com.example.tollparking;

import com.example.tollparking.api.park.ParkingService;
import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.validation.ParkingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Create ParkingException by providing a faulty request objects to api
 */
@SpringBootTest
public class TollParkingApplicationFaultTolaranceTest {

    @Test
    public void testParkFaultyRequest() {
        ParkingService parkingService = ParkingService.getInstance();
        Request        parkRequest    = new Request();
        parkRequest.setVehicle(null);
        Assertions.assertThrows(ParkingException.class, () -> parkingService.park(parkRequest));
    }


    @Test
    public void testUnParkFaultyRequest() throws ParkingException {
        ParkingService parkingService = ParkingService.getInstance();
        Request        unparkRequest  = new Request();
        unparkRequest.setVehicle(null);
        Assertions.assertThrows(ParkingException.class, () -> parkingService.unPark(unparkRequest));
    }


    @Test
    public void testNullParkRequest() throws ParkingException {
        ParkingService parkingService = ParkingService.getInstance();
        Assertions.assertThrows(ParkingException.class, () -> parkingService.park(null));
    }


    @Test
    public void testNullUnParkRequest() throws ParkingException {
        ParkingService parkingService = ParkingService.getInstance();
        Assertions.assertThrows(ParkingException.class, () -> parkingService.park(null));
    }

}

package com.example.tollparking;

import com.example.tollparking.api.constants.ParkingConstants;
import com.example.tollparking.api.park.ParkingService;
import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.vehicle.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.tollparking.api.requestresponse.ResponseCodes.PARK_SUCCESS_CODE;

/**
 * for each slot type testing performance by parking and un-parking 1k vehicle to see how much time it will take to
 * handle..
 */
@SpringBootTest
public class TollParkingApplicationPerformanceTest {

    private static final int TEST_COUNT = 1000;

    /**
     * Test throughput  performance for sedan
     */
    @Test
    public void test2SecondsPerformanceForSedanSlots() {

        AtomicInteger testParkCount   = new AtomicInteger(0);
        AtomicInteger testUnParkCount = new AtomicInteger(0);

        Executable sedan = () -> {
            final ParkingService parkingService = ParkingService.getInstance();
            Request              parkRequest    = new Request();
            parkRequest.setVehicle(new Vehicle(ParkingConstants.SEDAN));
            for (int i = 0; i < TEST_COUNT; i++) {
                Response parkingResponse = parkingService.park(parkRequest);
                testParkCount.incrementAndGet();
                if (parkingResponse.getStatus() == PARK_SUCCESS_CODE) {
                    parkingService.unPark(parkRequest);
                    testUnParkCount.incrementAndGet();
                }
            }
        };
        
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), sedan, "For sedan; 100 operations did not finish in expected time limit. Performance impact. Unpark count: " + testUnParkCount.get() + ", parkcount: " + testParkCount.get());
        Assertions.assertTrue(testParkCount.get() == testUnParkCount.get(), "Sedan, Parking and UnParking operations did not aligned during 1k performance test.");
    }


    /**
     * Test throughput  performance for EC20WATT
     */
    @Test
    public void test2SecondsPerformanceForEC20WSlots() {

        AtomicInteger testParkCount   = new AtomicInteger(0);
        AtomicInteger testUnParkCount = new AtomicInteger(0);

        Executable ec20 = () -> {
            final ParkingService parkingService = ParkingService.getInstance();
            Request              parkRequest    = new Request();
            parkRequest.setVehicle(new Vehicle(ParkingConstants.EC20WATT));
            for (int i = 0; i < TEST_COUNT; i++) {
                Response parkingResponse = parkingService.park(parkRequest);
                testParkCount.incrementAndGet();
                if (parkingResponse.getStatus() == PARK_SUCCESS_CODE) {
                    parkingService.unPark(parkRequest);
                    testUnParkCount.incrementAndGet();
                }
            }
        };

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), ec20, "For ec20Watt; 100 operations did not finish in expected time limit. Performance impact. Unpark count: " + testUnParkCount.get() + ", parkcount: " + testParkCount.get());
        Assertions.assertTrue(testParkCount.get() == testUnParkCount.get(), "EC20KWatt, Parking and UnParking operations did not aligned during 1k performance test.");
    }


    /**
     * Test throughput  performance for EC50WATT
     */
    @Test
    public void test2SecondsPerformanceForEC50WSlots() {

        AtomicInteger testParkCount   = new AtomicInteger(0);
        AtomicInteger testUnParkCount = new AtomicInteger(0);

        Executable ec50 = () -> {
            final ParkingService parkingService = ParkingService.getInstance();
            Request              parkRequest    = new Request();
            parkRequest.setVehicle(new Vehicle(ParkingConstants.EC50WATT));
            for (int i = 0; i < TEST_COUNT; i++) {
                Response parkingResponse = parkingService.park(parkRequest);
                testParkCount.incrementAndGet();
                if (parkingResponse.getStatus() == PARK_SUCCESS_CODE) {
                    parkingService.unPark(parkRequest);
                    testUnParkCount.incrementAndGet();
                }
            }
        };

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), ec50, "For ec50Watt; 1k operations did not finish in expected time limit. Performance impact.  Unpark count: " + testUnParkCount.get() + ", parkcount: " + testParkCount.get());
        Assertions.assertTrue(testParkCount.get() == testUnParkCount.get(), "EC50KWatt, Parking and UnParking operations did not aligned during 1k performance test.");
    }
}

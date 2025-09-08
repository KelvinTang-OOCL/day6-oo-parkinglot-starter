package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_ParkingTicket_when_park_given_not_full_ParkingLot_and_Car() throws NoAvailablePositionException {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        //When
        ParkingTicket parkingTicket = parkingLot.park(car);


        //Then
        assertNotNull(parkingTicket);
    }
}

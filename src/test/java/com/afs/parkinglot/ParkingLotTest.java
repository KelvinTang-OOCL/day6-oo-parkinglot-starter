package com.afs.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setErr(new PrintStream(outContent));
    }

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

    @Test
    void should_return_ParkingTicket_when_park_given_full_ParkingLot_and_Car() throws NoAvailablePositionException {
        //Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();

        //When
        NoAvailablePositionException noAvailablePositionException = assertThrows(
                NoAvailablePositionException.class,
                () -> parkingLot.park(car)
        );

        //Then
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }

    @Test
    void should_return_ParkingTicket_when_park_given_not_full_ParkingLot_and_null_Car() throws NoAvailablePositionException {
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = null;

        //When

        //Then
        assertNull(parkingLot.park(car));
    }
}

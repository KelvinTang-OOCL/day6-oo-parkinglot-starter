package com.afs.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

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
    void should_thrown_noAvailablePositionException_when_park_given_full_ParkingLot_and_Car() throws NoAvailablePositionException {
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
    void should_return_null_when_park_given_not_full_ParkingLot_and_null_Car() throws NoAvailablePositionException {
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = null;

        //When

        //Then
        assertNull(parkingLot.park(car));
    }

    @Test
    void should_return_null_when_park_given_not_full_ParkingLot_and_parked_Car() throws NoAvailablePositionException {
        //Given
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();

        //When
        parkingLot.park(car);

        //Then
        assertNull(parkingLot.park(car));
    }

    @Test
    void should_return_Car_when_fetch_given_correct_ticket() throws NoAvailablePositionException, UnrecognizedParkingTicketException {
        //Given
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //When
        Car fetchedCat = parkingLot.fetch(parkingTicket);

        //Then
        assertEquals(car, fetchedCat);
    }
}

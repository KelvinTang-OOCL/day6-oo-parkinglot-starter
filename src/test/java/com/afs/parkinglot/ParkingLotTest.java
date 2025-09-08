package com.afs.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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
    void should_thrown_NoAvailablePositionException_when_park_given_full_ParkingLot_and_Car() throws NoAvailablePositionException {
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

    @Test
    void should_throw_UnrecognizedParkingTicketException_when_fetch_given_invalid_ticket() throws UnrecognizedParkingTicketException {
        //Given
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();

        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(
                UnrecognizedParkingTicketException.class,
                () -> parkingLot.fetch(parkingTicket)
        );

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_UnrecognizedParkingTicketException_when_fetch_given_null_ticket() throws UnrecognizedParkingTicketException {
        //Given
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        ParkingTicket parkingTicket = null;

        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(
                UnrecognizedParkingTicketException.class,
                () -> parkingLot.fetch(parkingTicket)
        );

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_UnrecognizedParkingTicketException_when_fetch_given_used_ticket() throws UnrecognizedParkingTicketException, NoAvailablePositionException {
        //Given
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetch(parkingTicket);

        //When

        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(
                UnrecognizedParkingTicketException.class,
                () -> parkingLot.fetch(parkingTicket)
        );

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_ParkingTicket_when_park_given_StandardParkingBoy_and_any_not_full_ParkingLot_and_Car() throws UnrecognizedParkingTicketException, NoAvailablePositionException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(1)
        ));
        Car car = new Car();

        //When
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_thrown_NoAvailablePositionException_when_park_given_StandardParkingBoy_and_full_ParkingLot_and_Car() throws UnrecognizedParkingTicketException, NoAvailablePositionException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(0)
        ));
        Car car = new Car();

        //When
        NoAvailablePositionException noAvailablePositionException = assertThrows(
                NoAvailablePositionException.class,
                () -> standardParkingBoy.park(car)
        );

        //Then
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }

    @Test
    void should_return_null_when_park_given_StandardParkingBoy_and_not_full_ParkingLot_and_null_Car() throws NoAvailablePositionException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(1)
        ));
        Car car = null;

        //When

        //Then
        assertNull(standardParkingBoy.park(car));
    }

    @Test
    void should_return_null_when_park_given_StandardParkingBoy_and_not_full_ParkingLot_and_parked_Car() throws NoAvailablePositionException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(2)
        ));
        Car car = new Car();

        //When
        standardParkingBoy.park(car);

        //Then
        assertNull(standardParkingBoy.park(car));
    }

    @Test
    void should_return_Car_when_fetch_given_StandardParkingBoy_and_correct_ticket() throws NoAvailablePositionException, UnrecognizedParkingTicketException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(2)
        ));
        Car car = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        //When
        Car fetchedCat = standardParkingBoy.fetch(parkingTicket);

        //Then
        assertEquals(car, fetchedCat);
    }

    @Test
    void should_throw_UnrecognizedParkingTicketException_when_fetch_given_StandardParkingBoy_and_invalid_ticket() throws UnrecognizedParkingTicketException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(2)
        ));
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();

        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(
                UnrecognizedParkingTicketException.class,
                () -> standardParkingBoy.fetch(parkingTicket)
        );

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_UnrecognizedParkingTicketException_when_fetch_given_StandardParkingBoy_and_null_ticket() throws UnrecognizedParkingTicketException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(2)
        ));
        Car car = new Car();
        ParkingTicket parkingTicket = null;

        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(
                UnrecognizedParkingTicketException.class,
                () -> standardParkingBoy.fetch(parkingTicket)
        );

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_UnrecognizedParkingTicketException_when_fetch_given_StandardParkingBoy_and_used_ticket() throws UnrecognizedParkingTicketException, NoAvailablePositionException {
        //Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(
                new ParkingLot(0),
                new ParkingLot(0),
                new ParkingLot(2)
        ));
        Car car = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(parkingTicket);

        //When

        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(
                UnrecognizedParkingTicketException.class,
                () -> standardParkingBoy.fetch(parkingTicket)
        );

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
}

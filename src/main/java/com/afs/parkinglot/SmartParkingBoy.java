package com.afs.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) throws NoAvailablePositionException {

        ParkingLot availableParkingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingInt(ParkingLot::getCapacity))
                .orElse(null);

        if (availableParkingLot == null) {
            throw new NoAvailablePositionException("No available position.");
        }

        return availableParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException {
        for (ParkingLot parkingLot: parkingLots) {
            try {
                return parkingLot.fetch(parkingTicket);
            } catch (UnrecognizedParkingTicketException ignored) {
            }
        }
        throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
    }
}

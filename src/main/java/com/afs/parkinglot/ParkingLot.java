package com.afs.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private final Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public  ParkingTicket park(Car car) throws NoAvailablePositionException {
        if (isFull()) {
            throw new NoAvailablePositionException("No available position.");
        }
        if (car == null || parkedCars.containsValue(car)){
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedCars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException {
        if (parkedCars.containsKey(parkingTicket)) {
            return parkedCars.remove(parkingTicket);
        }
        throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
    }

    public boolean isFull() {
        return parkedCars.size() >= capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableCapacity() {
        return capacity-parkedCars.size();
    }

    public float getAvailableCapacityRatio() {
        return (float) getAvailableCapacity() /capacity;
    }

}

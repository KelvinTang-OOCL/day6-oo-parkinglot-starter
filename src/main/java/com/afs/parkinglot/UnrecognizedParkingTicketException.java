package com.afs.parkinglot;

public class UnrecognizedParkingTicketException extends Exception {
    public UnrecognizedParkingTicketException(String message) {
        super(message);
    }
}

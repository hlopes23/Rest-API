package com.example.car_shop.exception;

public class VehicleDoesNotExistException extends RuntimeException {
    public VehicleDoesNotExistException(String message) {
        super(message);
    }
}

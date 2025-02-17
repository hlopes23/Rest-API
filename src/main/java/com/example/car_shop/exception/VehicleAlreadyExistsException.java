package com.example.car_shop.exception;

public class VehicleAlreadyExistsException extends RuntimeException {

    public VehicleAlreadyExistsException(String message) {
        super(message);
    }
}

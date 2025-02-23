package com.example.car_shop.exception;

public class VehicleAlreadyAssociatedToAccount extends RuntimeException {
    public VehicleAlreadyAssociatedToAccount(String message) {
        super(message);
    }
}

package com.example.car_shop.exception;

public class VehicleAssociatedToAccount extends RuntimeException {
    public VehicleAssociatedToAccount(String message) {
        super(message);
    }
}

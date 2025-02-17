package com.example.car_shop.exception;

public class AccountAlreadyActivatedDeactivatedException extends RuntimeException {
    public AccountAlreadyActivatedDeactivatedException(String message) {
        super(message);
    }
}

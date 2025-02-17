package com.example.car_shop.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}

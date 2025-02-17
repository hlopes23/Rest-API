package com.example.car_shop.exception;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}

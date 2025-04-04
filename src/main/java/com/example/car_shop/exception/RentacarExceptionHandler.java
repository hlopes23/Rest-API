package com.example.car_shop.exception;

import com.example.car_shop.model.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RentacarExceptionHandler {

    @ExceptionHandler(value = {
            AccountDoesNotExistException.class,
            VehicleDoesNotExistException.class})

    public ResponseEntity<ErrorDTO> handleNotFoundException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorDTO.builder()
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .build());
    }


    @ExceptionHandler(value = {
            AccountAlreadyExistsException.class,
            VehicleAlreadyExistsException.class})

    public ResponseEntity<ErrorDTO> handleAlreadyExistsException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ErrorDTO.builder()
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(value = {
            VehicleAssociatedToAccount.class})

    public ResponseEntity<ErrorDTO> handleVehicleAssociatedToAccountException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                ErrorDTO.builder()
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .build());
    }

}

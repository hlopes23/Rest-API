package com.example.car_shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDTO {
    private Date timestamp;
    private String message;
    private String method;
    private String path;

    public ErrorDTO(String message) {
        this.message = message;
    }
}

package com.example.car_shop.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiclePlateDTO {

    @NotNull(message = "License Plate must not be null")
    private String licensePlate;
}

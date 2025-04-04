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
public class VehicleDTO {


    @NotNull(message = "Mandatory field.")
    public String brand;

    @NotNull(message = "Mandatory field.")
    public String licensePlate;

    @NotNull(message = "Mandatory field.")
    public String year;

    public boolean active;
    public AccountDTO accountDTO;

}
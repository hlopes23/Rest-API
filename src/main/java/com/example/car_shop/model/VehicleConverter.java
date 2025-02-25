package com.example.car_shop.model;

import com.example.car_shop.entity.Vehicle;

public class VehicleConverter {

    public static VehicleDTO fromVehicleToVehicleDTO(Vehicle vehicle) {
        return VehicleDTO.builder()
                .brand(vehicle.getBrand())
                .year(vehicle.getYear())
                .licensePlate(vehicle.getLicensePlate())
                .active(vehicle.isActive())
                .accountDTO(AccountConverter.fromAccountToAccountDto(vehicle.getAccount()))
                .build();
    }

    public static Vehicle fromVehicleDTOtoVehicle(VehicleDTO vehicleDTO) {
        return Vehicle.builder()
                .brand(vehicleDTO.getBrand())
                .year(vehicleDTO.getYear())
                .licensePlate(vehicleDTO.getLicensePlate())
                .build();
    }

    public static VehiclePlateDTO fromVehicleToVehiclePlateDTO(Vehicle vehicle) {
        return VehiclePlateDTO.builder()
                .licensePlate(vehicle.getLicensePlate())
                .build();
    }
}

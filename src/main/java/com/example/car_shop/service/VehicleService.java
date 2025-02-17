package com.example.car_shop.service;

import com.example.car_shop.entity.Vehicle;
import com.example.car_shop.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        this.vehicleRepository.findAll().forEach(vehicles::add);
        return vehicles;
    }


    /*public void createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Optional<Vehicle> byLicensePlate = vehicleRepository.findByLicensePlate(vehicleDTO.getLicensePlate());

        if (byLicensePlate.isPresent()) {
            throw new VehicleAlreadyExistsException("This Vehicle already exists with license plate: " + vehicleDTO.getLicensePlate());
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setActive(false);
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicleRepository.save(vehicle);
    }*/
}
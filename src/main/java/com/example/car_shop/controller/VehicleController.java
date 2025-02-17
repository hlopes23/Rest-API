package com.example.car_shop.controller;

import com.example.car_shop.entity.Vehicle;
import com.example.car_shop.service.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "car-shop/v1")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

   /* @PostMapping(path = "/vehicles")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDTO vehicleDto) {
        try {
            this.vehicleService.createVehicle(vehicleDto);
        } catch (VehicleAlreadyExistsException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO);
        }
        return ResponseEntity.status(201).build();
    }*/


    @GetMapping(path = "/vehicles")
    public List<Vehicle> getVehicles() {
        return this.vehicleService.getAllVehicles();
    }


    /*@GetMapping(path = "/{VehicleID}")
    public Vehicle getVehicleById(@PathVariable("VehicleID") Long id) {
        return this.vehicleService.getVehicle(id);
    }*/


}

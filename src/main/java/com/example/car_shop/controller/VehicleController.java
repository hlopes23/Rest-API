package com.example.car_shop.controller;

import com.example.car_shop.exception.AccountDoesNotExistException;
import com.example.car_shop.exception.VehicleAlreadyAssociatedToAccount;
import com.example.car_shop.exception.VehicleAlreadyExistsException;
import com.example.car_shop.exception.VehicleDoesNotExistException;
import com.example.car_shop.model.ErrorDTO;
import com.example.car_shop.model.VehicleDTO;
import com.example.car_shop.service.VehicleService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping(path = "/car-shop/v1")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(path = "/vehicles")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDTO vehicleDto) {
        try {
            this.vehicleService.createVehicle(vehicleDto);
        } catch (VehicleAlreadyExistsException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO.getMessage());
        }
        return ResponseEntity.status(201).body("Vehicle created successfully!");
    }


    @GetMapping(path = "/vehicles")
    public ResponseEntity<?> getAllvehicles() {

        List<VehicleDTO> vehicles = this.vehicleService.getAllVehicles();

        return ResponseEntity.status(200).body(vehicles);
    }


    @PatchMapping(path = "vehicles/activate/{id}")
    public ResponseEntity<?> activateVehicle(@PathVariable Long id) {
        try {
            this.vehicleService.activateVehicle(id);
        } catch (VehicleDoesNotExistException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO.getMessage());
        }
        return ResponseEntity.status(200).body("Vehicle " + id + " activated!");
    }


    @PatchMapping(path = "vehicles/deactivate/{id}")
    public ResponseEntity<?> deactivateVehicle(@PathVariable Long id) {
        try {
            this.vehicleService.deactivateVehicle(id);
        } catch (VehicleDoesNotExistException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO.getMessage());
        }
        return ResponseEntity.status(200).body("Vehicle " + id + " deactivated!");
    }


    @PatchMapping(path = "vehicles/{vehicleId}/{accountId}")
    public ResponseEntity<?> setAccountToVehicle(@PathVariable Long vehicleId, @PathVariable Long accountId) {

        try {
            this.vehicleService.setAccountToVehicle(vehicleId, accountId);
        } catch (VehicleDoesNotExistException | AccountDoesNotExistException | VehicleAlreadyAssociatedToAccount e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO.getMessage());
        }
        return ResponseEntity.status(200).body("Account " + accountId + " associated to Vehicle " + vehicleId + ".");
    }
}

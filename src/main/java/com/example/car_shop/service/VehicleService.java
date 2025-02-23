package com.example.car_shop.service;

import com.example.car_shop.entity.Account;
import com.example.car_shop.entity.Vehicle;
import com.example.car_shop.exception.AccountDoesNotExistException;
import com.example.car_shop.exception.VehicleAlreadyAssociatedToAccount;
import com.example.car_shop.exception.VehicleAlreadyExistsException;
import com.example.car_shop.exception.VehicleDoesNotExistException;
import com.example.car_shop.model.VehicleConverter;
import com.example.car_shop.model.VehicleDTO;
import com.example.car_shop.repository.AccountRepository;
import com.example.car_shop.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    public Object setAccountToVehicle;
    private VehicleRepository vehicleRepository;
    private AccountRepository accountRepository;

    public VehicleService(VehicleRepository vehicleRepository, AccountRepository accountRepository) {
        this.vehicleRepository = vehicleRepository;
        this.accountRepository = accountRepository;
    }

    // METHOD TO GET ALL VEHICLES
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        this.vehicleRepository.findAll().forEach(vehicles::add);

        List<VehicleDTO> vehiclesDTO = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            vehiclesDTO.add(VehicleConverter.fromVehicleToVehicleDTO(vehicle));
        }
        return vehiclesDTO;
    }


    // METHOD TO CREATE VEHICLE
    public void createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Optional<Vehicle> byLicensePlate = vehicleRepository.findByLicensePlate(vehicleDTO.getLicensePlate());

        if (byLicensePlate.isPresent()) {
            throw new VehicleAlreadyExistsException("This Vehicle already exists with license plate: " + vehicleDTO.getLicensePlate());
        }

        Vehicle vehicle = VehicleConverter.fromVehicleDTOtoVehicle(vehicleDTO);
        vehicleRepository.save(vehicle);
    }


    // METHOD TO DEACTIVATE VEHICLE

    public void deactivateVehicle(Long id) {

        Optional<Vehicle> vehicleOpt = this.vehicleRepository.findById(id);

        if (vehicleOpt.isEmpty()) {
            throw new VehicleDoesNotExistException("This vehicle does not exist in Repository.");
        }

        Vehicle vehicle = vehicleOpt.get();

        if (vehicle.isActive()) {
            vehicle.setActive(false);
            this.vehicleRepository.save(vehicle);
        }
    }


    // METHOD TO ACTIVATE VEHICLE

    public void activateVehicle(Long id) {
        Optional<Vehicle> vehicleOpt = this.vehicleRepository.findById(id);

        if (vehicleOpt.isEmpty()) {
            throw new VehicleDoesNotExistException("This vehicle does not exist in Repository.");
        }

        Vehicle vehicle = vehicleOpt.get();

        if (!vehicle.isActive()) {
            vehicle.setActive(true);
            this.vehicleRepository.save(vehicle);
        }
    }


    //METHOD TO ASSOCIATE ACCOUNT TO VEHICLE

    public void setAccountToVehicle(Long vehicleId, Long accountId) {

        Optional<Vehicle> vehicleOpt = this.vehicleRepository.findById(vehicleId);

        if (vehicleOpt.isEmpty()) {
            throw new VehicleDoesNotExistException("This vehicle does not exist in Repository.");
        }

        Optional<Account> accountOpt = this.accountRepository.findById(accountId);

        if (accountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("This account does not exist in Repository.");
        }

        Vehicle vehicle = vehicleOpt.get();

        if (vehicle.getAccount() != null) {
            throw new VehicleAlreadyAssociatedToAccount("This Vehicle already has Account " + vehicle.getAccount().getId() + " associated.");
        }

        vehicle.setAccount(accountOpt.get());
        vehicleRepository.save(vehicle);
    }
}
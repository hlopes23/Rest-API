package com.example.car_shop.repository;

import com.example.car_shop.entity.Account;
import com.example.car_shop.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByLicensePlate(String licensePlate);

    Optional<List<Vehicle>> findByAccountIn(Optional<List<Account>> accounts);
}

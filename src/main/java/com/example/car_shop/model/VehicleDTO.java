package com.example.car_shop.model;

import lombok.Builder;

@Builder
public class VehicleDTO {

    private String brand;
    private String licensePlate;
    private String year;

    public VehicleDTO(String brand, String licensePlate, String year) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.year = year;
    }

    public VehicleDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

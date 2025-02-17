package com.example.car_shop.model;

import lombok.Builder;

@Builder
public class AccountDTO {

    public String firstname;
    public String lastname;
    public String nif;
    public Boolean active;

    public AccountDTO(String firstname, String lastname, String nif, boolean active) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nif = nif;
        this.active = active;
    }

    public AccountDTO() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

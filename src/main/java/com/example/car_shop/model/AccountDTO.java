package com.example.car_shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    public String firstname;
    public String lastname;
    public String nif;
    public boolean active;
}

package com.example.car_shop.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @NotBlank(message = "Must have first name.")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String firstname;


    @NotBlank(message = "Must have last name.")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String lastname;


    @NotBlank(message = "Nif must not be null.")
    @Pattern(regexp = "^[1-9][0-9]*$")
    private String nif;

    @NotBlank(message = "username must not be null.")
    private String username;

    @NotBlank(message = "password must not be null.")
    private String password;

    private boolean active;
}

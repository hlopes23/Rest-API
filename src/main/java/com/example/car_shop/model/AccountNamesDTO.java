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
public class AccountNamesDTO {


    @NotBlank(message = "Must have first name.")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String firstname;

    @NotBlank(message = "Must have last name.")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String lastname;

}

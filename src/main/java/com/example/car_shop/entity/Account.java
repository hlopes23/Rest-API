package com.example.car_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "accounts")
@Table
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column(unique = true)
    private String nif;
    @Column
    private boolean active;
    @OneToMany(mappedBy = "account")
    private List<Vehicle> vehicle;

}

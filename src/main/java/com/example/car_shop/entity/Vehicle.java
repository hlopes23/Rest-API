package com.example.car_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String brand;
    @Column(unique = true, nullable = false)
    private String licensePlate;
    @Column
    private String year;
    @Column
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}

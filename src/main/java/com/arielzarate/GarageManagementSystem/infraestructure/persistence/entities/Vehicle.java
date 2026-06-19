package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String licensePlate;

    private String brand;

    private String model;

    private Integer year;

    private String version;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String color;

    private Integer kilometers;

    private String chassisNumber;

    private String engineNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

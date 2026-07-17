package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import com.arielzarate.GarageManagementSystem.domain.model.enums.FuelType;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
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

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelEntity model;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private VersionEntity version;

    private Integer year;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String color;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private Integer kilometers;

    private String chassisNumber;

    private String engineNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

}

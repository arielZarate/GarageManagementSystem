package com.arielzarate.GarageManagementSystem.domain.model;

import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private Long id;
    private String licensePlate;
    private String brand;
    private String model;
    private Integer year;
    private String version;
    private VehicleType vehicleType;
    private String color;
    private Integer kilometers;
    private String chassisNumber;
    private String engineNumber;
    private Long customerId;
}

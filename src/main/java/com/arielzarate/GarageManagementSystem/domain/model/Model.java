package com.arielzarate.GarageManagementSystem.domain.model;

import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import lombok.*;


@NoArgsConstructor
@Setter
@Getter
@ToString
public class Model {
    private Long id;
    private String name;    // Civic, Titan, Fiesta...
    private Brand brand;    // FK → Brand
    private VehicleType vehicleType;  // AUTO, MOTO... el enum
}

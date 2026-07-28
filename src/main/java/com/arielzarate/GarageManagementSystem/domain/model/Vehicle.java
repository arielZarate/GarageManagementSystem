package com.arielzarate.GarageManagementSystem.domain.model;

import com.arielzarate.GarageManagementSystem.domain.model.enums.FuelType;
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
    private Brand brand;
    private Model model;
    private Integer year;
    private Version version;
    //private VehicleType vehicleType;  // no se saca dejar comentado
    private String color;
    private FuelType fuelType;
    private Integer kilometers;
    private String chassisNumber;
    private String engineNumber;
    private Long customerId;
}

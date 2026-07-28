package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle;

import com.arielzarate.GarageManagementSystem.domain.model.enums.FuelType;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleRequest {
    private Long id;
    private String licensePlate;
    private Long brandId;
    private Long modelId;
    private Long versionId;
    private Integer year;
    private VehicleType vehicleType;
    private String color;
    private FuelType fuelType;
    private Integer kilometers;
    private String chassisNumber;
    private String engineNumber;
    private Long customerId;
}

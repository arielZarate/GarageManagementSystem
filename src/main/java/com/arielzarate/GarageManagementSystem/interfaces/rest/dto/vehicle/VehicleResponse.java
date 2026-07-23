package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle;

import com.arielzarate.GarageManagementSystem.domain.model.enums.FuelType;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String brandName;
    private String modelName;
    private String versionName;
    private Integer year;
    private String vehicleType;
    private String color;
    private String fuelType;
    private Integer kilometers;
    private String chassisNumber;
    private String engineNumber;
    private Long customerId;
    private String customerName;
}

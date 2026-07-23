package com.arielzarate.GarageManagementSystem.interfaces.rest.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;
import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.Version;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleResponse;
import org.springframework.stereotype.Component;

@Component
public class VehicleDTOMapper {

    public Vehicle toDomain(VehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(request.getId());
        vehicle.setLicensePlate(request.getLicensePlate());
        if (request.getBrandId() != null) {
            Brand brand = new Brand();
            brand.setId(request.getBrandId());
            vehicle.setBrandName(brand);
        }
        if (request.getModelId() != null) {
            Model model = new Model();
            model.setId(request.getModelId());
            vehicle.setModelName(model);
        }
        if (request.getVersionId() != null) {
            Version version = new Version();
            version.setId(request.getVersionId());
            vehicle.setVersionName(version);
        }
        vehicle.setYear(request.getYear());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setColor(request.getColor());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setKilometers(request.getKilometers());
        vehicle.setChassisNumber(request.getChassisNumber());
        vehicle.setEngineNumber(request.getEngineNumber());
        vehicle.setCustomerId(request.getCustomerId());
        return vehicle;
    }

    public VehicleResponse toResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .licensePlate(vehicle.getLicensePlate())
                .brandName(vehicle.getBrandName() != null ? vehicle.getBrandName().getName() : null)
                .modelName(vehicle.getModelName() != null ? vehicle.getModelName().getName() : null)
                .versionName(vehicle.getVersionName() != null ? vehicle.getVersionName().getName() : null)
                .year(vehicle.getYear())
                .vehicleType(vehicle.getVehicleType() != null ? vehicle.getVehicleType().getDisplayName() : null)
                .color(vehicle.getColor())
                .fuelType(vehicle.getFuelType() != null ? vehicle.getFuelType().getDisplayName() : null)
                .kilometers(vehicle.getKilometers())
                .chassisNumber(vehicle.getChassisNumber())
                .engineNumber(vehicle.getEngineNumber())
                .customerId(vehicle.getCustomerId())
                .build();
    }
}

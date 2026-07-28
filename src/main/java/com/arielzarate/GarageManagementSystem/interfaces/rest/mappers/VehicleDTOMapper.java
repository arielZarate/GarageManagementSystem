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
            vehicle.setBrand(brand);
        }
        if (request.getModelId() != null) {
            Model model = new Model();
            model.setId(request.getModelId());
            vehicle.setModel(model);
        }
        if (request.getVersionId() != null) {
            Version version = new Version();
            version.setId(request.getVersionId());
            vehicle.setVersion(version);
        }
        vehicle.setYear(request.getYear());
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
                .brandName(vehicle.getBrand() != null ? vehicle.getBrand().getName() : null)
                .modelName(vehicle.getModel() != null ? vehicle.getModel().getName() : null)
                .versionName(vehicle.getVersion() != null ? vehicle.getVersion().getName() : null)
                .year(vehicle.getYear())
                .vehicleType(vehicle.getModel() != null ? vehicle.getModel().getVehicleType().getDisplayName() : null)
                .color(vehicle.getColor())
                .fuelType(vehicle.getFuelType() != null ? vehicle.getFuelType().getDisplayName() : null)
                .kilometers(vehicle.getKilometers())
                .chassisNumber(vehicle.getChassisNumber())
                .engineNumber(vehicle.getEngineNumber())
                .customerId(vehicle.getCustomerId())
                .build();
    }
}

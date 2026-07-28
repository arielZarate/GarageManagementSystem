package com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleMapper {

    private final BrandMapper brandMapper;
    private final ModelMapper modelMapper;
    private final VersionMapper versionMapper;

    public Vehicle toDomain(com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.Vehicle entity) {
        Vehicle domain = new Vehicle();
        domain.setId(entity.getId());
        domain.setLicensePlate(entity.getLicensePlate());
        domain.setBrand(brandMapper.toDomain(entity.getBrand()));
        domain.setModel(modelMapper.toDomain(entity.getModel()));
        domain.setVersion(versionMapper.toDomain(entity.getVersion()));
        domain.setYear(entity.getYear());
        //domain.setVehicleType(entity.getVehicleType());
        domain.setColor(entity.getColor());
        domain.setFuelType(entity.getFuelType());
        domain.setKilometers(entity.getKilometers());
        domain.setChassisNumber(entity.getChassisNumber());
        domain.setEngineNumber(entity.getEngineNumber());
        domain.setCustomerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null);
        return domain;
    }

    public com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.Vehicle toEntity(
            Vehicle domain,
            BrandEntity brand,
            ModelEntity model,
            VersionEntity version,
            CustomerEntity customer
    ) {
        com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.Vehicle entity =
                new com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.Vehicle();
        entity.setId(domain.getId());
        entity.setLicensePlate(domain.getLicensePlate());
        entity.setBrand(brand);
        entity.setModel(model);
        entity.setVersion(version);
        entity.setYear(domain.getYear());
      //  entity.setVehicleType(domain.getVehicleType());
        entity.setColor(domain.getColor());
        entity.setFuelType(domain.getFuelType());
        entity.setKilometers(domain.getKilometers());
        entity.setChassisNumber(domain.getChassisNumber());
        entity.setEngineNumber(domain.getEngineNumber());
        entity.setCustomer(customer);
        return entity;
    }
}

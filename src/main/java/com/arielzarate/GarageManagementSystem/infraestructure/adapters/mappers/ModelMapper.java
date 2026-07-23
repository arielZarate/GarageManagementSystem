package com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.BrandEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.ModelEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    private final BrandMapper brandMapper;

    public ModelMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public Model toDomain(ModelEntity entity) {
        Model domain = new Model();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setBrand(brandMapper.toDomain(entity.getBrand()));
        domain.setVehicleType(entity.getVehicleType());
        return domain;
    }

    public ModelEntity mapToEntity(String name, BrandEntity brand, VehicleType vehicleType) {
                ModelEntity entity = new ModelEntity();
                entity.setName(name);
                entity.setBrand(brand);
                entity.setVehicleType(vehicleType);

                return entity;
    }

}

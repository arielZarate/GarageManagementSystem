package com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Version;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.ModelEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.VersionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VersionMapper {

    private final ModelMapper modelMapper;

    public Version toDomain(VersionEntity entity) {
        Version domain = new Version();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setModel(modelMapper.toDomain(entity.getModel()));
        return domain;
    }

    public VersionEntity toEntity(String name, ModelEntity model) {
        VersionEntity entity = new VersionEntity();
        entity.setName(name);
        entity.setModel(model);
        return entity;
    }
}

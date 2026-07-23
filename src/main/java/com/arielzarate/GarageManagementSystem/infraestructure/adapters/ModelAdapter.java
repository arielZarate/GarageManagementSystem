package com.arielzarate.GarageManagementSystem.infraestructure.adapters;

import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.out.ModelProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.ModelMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.BrandEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.ModelEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.BrandRepository;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ModelAdapter implements ModelProvider {

    private final ModelRepository repository;
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    @Override
    public Model create(String name, Long brandId, VehicleType vehicleType) {
        BrandEntity brand = brandRepository.findById(brandId).get();
        ModelEntity entity = mapper.mapToEntity(name, brand, vehicleType);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Model> update(Long id, String name, VehicleType vehicleType) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(name);
                    entity.setVehicleType(vehicleType);
                    return mapper.toDomain(repository.save(entity));
                });
    }

    @Override
    public Optional<Model> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Model> findByBrandId(Long brandId) {
        return repository.findByBrandId(brandId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Model> findByVehicleType(VehicleType vehicleType) {
        return repository.findByVehicleType(vehicleType)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Model> findByBrandIdAndVehicleType(Long brandId, VehicleType vehicleType) {
        return repository.findByBrandIdAndVehicleType(brandId, vehicleType)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Model> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

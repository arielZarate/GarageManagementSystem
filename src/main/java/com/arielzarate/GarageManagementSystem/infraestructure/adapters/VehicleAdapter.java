package com.arielzarate.GarageManagementSystem.infraestructure.adapters;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.out.VehicleProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.VehicleMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.*;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VehicleAdapter implements VehicleProvider {

    private final VehicleRepository repository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final VersionRepository versionRepository;
    private final CustomerRepository customerRepository;
    private final VehicleMapper mapper;

    @Override
    public Vehicle create(Vehicle vehicle) {
        BrandEntity brand = brandRepository.findById(vehicle.getBrandName().getId()).get();
        ModelEntity model = modelRepository.findById(vehicle.getModelName().getId()).get();
        VersionEntity version = vehicle.getVersionName() != null
                ? versionRepository.findById(vehicle.getVersionName().getId()).get()
                : null;
        CustomerEntity customer = vehicle.getCustomerId() != null
                ? customerRepository.findById(vehicle.getCustomerId()).get()
                : null;

        var entity = mapper.toEntity(vehicle, brand, model, version, customer);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Vehicle> update(Vehicle vehicle) {
        return repository.findById(vehicle.getId())
                .map(existing -> {
                    BrandEntity brand = brandRepository.findById(vehicle.getBrandName().getId()).get();
                    ModelEntity model = modelRepository.findById(vehicle.getModelName().getId()).get();
                    VersionEntity version = vehicle.getVersionName() != null
                            ? versionRepository.findById(vehicle.getVersionName().getId()).get()
                            : null;
                    CustomerEntity customer = vehicle.getCustomerId() != null
                            ? customerRepository.findById(vehicle.getCustomerId()).get()
                            : null;

                    var entity = mapper.toEntity(vehicle, brand, model, version, customer);
                    entity.setId(existing.getId());
                    return mapper.toDomain(repository.save(entity));
                });
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Vehicle> findByLicensePlate(String licensePlate) {
        return repository.findByLicensePlate(licensePlate).map(mapper::toDomain);
    }

    @Override
    public List<Vehicle> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Vehicle> findByVehicleType(VehicleType type) {
        return repository.findByVehicleType(type)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Vehicle> findAll() {
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

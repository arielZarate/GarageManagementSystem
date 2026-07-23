package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public interface ModelProvider {

    Model create(String name, Long brandId, VehicleType vehicleType);

    Optional<Model> update(Long id, String name, VehicleType vehicleType);

    Optional<Model> findById(Long id);

    List<Model> findByBrandId(Long brandId);

    List<Model> findByVehicleType(VehicleType vehicleType);

    List<Model> findByBrandIdAndVehicleType(Long brandId, VehicleType vehicleType);

    List<Model> findAll();

    void deleteById(Long id);
}

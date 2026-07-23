package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public interface VehicleProvider {

    Vehicle create(Vehicle vehicle);

    Optional<Vehicle> update(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    Optional<Vehicle> findByLicensePlate(String licensePlate);

    List<Vehicle> findByCustomerId(Long customerId);

    List<Vehicle> findByVehicleType(VehicleType type);

    List<Vehicle> findAll();

    void deleteById(Long id);
}

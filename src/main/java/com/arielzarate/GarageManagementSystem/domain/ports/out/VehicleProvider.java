package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VehicleProvider {

    Vehicle create(Vehicle vehicle);

    Optional<Vehicle> update(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    Page<Vehicle> searchByLicensePlateOrDNI(String query, Pageable  pageable);
    Page<Vehicle> findByVehicleType(VehicleType type,Pageable pageable);
    Page<Vehicle> findAll(Pageable pageable);

    void deleteById(Long id);
}

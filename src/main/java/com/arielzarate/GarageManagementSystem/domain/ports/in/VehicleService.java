package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {


    Page<Vehicle> getVehicles(String query, VehicleType type, Pageable pageable);

    Vehicle addVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    Vehicle getVehicleById(Long id);

    void deleteVehicle(Long id);
}

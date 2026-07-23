package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;

import java.util.List;

public interface VehicleService {

    Vehicle addVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    Vehicle getVehicleById(Long id);

    List<Vehicle> getVehicles(String query, VehicleType type);

    List<Vehicle> getVehiclesByCustomer(Long customerId);

    void deleteVehicle(Long id);
}

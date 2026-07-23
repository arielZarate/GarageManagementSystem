package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;

import java.util.List;

public interface ModelService {

    Model addModel(String name, Long brandId, VehicleType vehicleType);

    Model updateModel(Long id, String name, VehicleType vehicleType);

    List<Model> getModels(Long brandId, VehicleType vehicleType);

    Model getModelById(Long id);

    void deleteModel(Long id);
}

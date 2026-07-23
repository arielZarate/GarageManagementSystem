package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.in.ModelService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.BrandProvider;
import com.arielzarate.GarageManagementSystem.domain.ports.out.ModelProvider;
import com.arielzarate.GarageManagementSystem.domain.services.StringCapitalize;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationErrorException;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ModelUseCase implements ModelService {

    private final ModelProvider provider;
    private final BrandProvider brandProvider;

    @Override
    public Model addModel(String name, Long brandId, VehicleType vehicleType) {
        if (name.isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El campo nombre no puede estar vacio."));
        }
        if (brandId == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar una marca."));
        }
        if (vehicleType == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar un tipo de vehículo."));
        }

        brandProvider.findById(brandId)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Marca no encontrada con el id: " + brandId)));

        try {
            return provider.create(StringCapitalize.capitalize(name), brandId, vehicleType);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationErrorException(ApplicationError.conflict("Ya existe un modelo con ese nombre para esta marca."));
        }
    }

    @Override
    public Model updateModel(Long id, String name, VehicleType vehicleType) {
        if (name.isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El campo nombre no puede estar vacio."));
        }
        if (vehicleType == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar un tipo de vehículo."));
        }

        return provider.update(id, StringCapitalize.capitalize(name), vehicleType)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Modelo no encontrado con el id: " + id)));
    }

    @Override
    public List<Model> getModels(Long brandId, VehicleType vehicleType) {
        if (brandId != null && vehicleType != null) {
            return provider.findByBrandIdAndVehicleType(brandId, vehicleType);
        } else if (brandId != null) {
            return provider.findByBrandId(brandId);
        } else if (vehicleType != null) {
            return provider.findByVehicleType(vehicleType);
        }
        return provider.findAll();
    }

    @Override
    public Model getModelById(Long id) {
        return provider.findById(id)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Modelo no encontrado con el id: " + id)));
    }

    @Override
    public void deleteModel(Long id) {
        provider.findById(id)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Modelo no encontrado con el id: " + id)));
        provider.deleteById(id);
        log.info("Modelo eliminado con el id: {}", id);
    }
}

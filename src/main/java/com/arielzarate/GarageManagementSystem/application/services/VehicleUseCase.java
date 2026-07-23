package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.application.errors.ApplicationError;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationErrorException;
import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VehicleService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.VehicleProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VehicleUseCase implements VehicleService {

    private final VehicleProvider provider;

    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("La patente no puede estar vacía."));
        }
        if (vehicle.getBrandName() == null || vehicle.getBrandName().getId() == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar una marca."));
        }
        if (vehicle.getModelName() == null || vehicle.getModelName().getId() == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar un modelo."));
        }
        if (vehicle.getYear() == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El año no puede estar vacío."));
        }

        try {
            return provider.create(vehicle);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationErrorException(ApplicationError.conflict("Ya existe un vehículo con esa patente."));
        }
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El ID del vehículo es requerido."));
        }

        provider.findById(vehicle.getId())
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError(
                        "Vehículo no encontrado con el id: " + vehicle.getId())));

        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("La patente no puede estar vacía."));
        }
        if (vehicle.getBrandName() == null || vehicle.getBrandName().getId() == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar una marca."));
        }
        if (vehicle.getModelName() == null || vehicle.getModelName().getId() == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar un modelo."));
        }

        try {
            return provider.update(vehicle)
                    .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError(
                            "Vehículo no encontrado con el id: " + vehicle.getId())));
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationErrorException(ApplicationError.conflict("Ya existe un vehículo con esa patente."));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle getVehicleById(Long id) {
        return provider.findById(id)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError(
                        "Vehículo no encontrado con el id: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehicles(String query, VehicleType type) {
        if (type != null) {
            return provider.findByVehicleType(type);
        }
        if (query != null && !query.isBlank()) {
            // buscamos por patente
            var byPlate = provider.findByLicensePlate(query.trim());
            return byPlate.map(List::of).orElseGet(List::of);
        }
        return provider.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByCustomer(Long customerId) {
        return provider.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        provider.findById(id)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError(
                        "Vehículo no encontrado con el id: " + id)));
        provider.deleteById(id);
        log.info("Vehículo eliminado con el id: {}", id);
    }
}

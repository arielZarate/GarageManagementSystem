package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.domain.model.Version;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VersionService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.ModelProvider;
import com.arielzarate.GarageManagementSystem.domain.ports.out.VersionProvider;
import com.arielzarate.GarageManagementSystem.domain.services.StringCapitalize;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationErrorException;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class VersionUseCase implements VersionService {

    private final VersionProvider provider;
    private final ModelProvider modelProvider;

    @Override
    public Version addVersion(String name, Long modelId) {
        if (name.isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El nombre no puede estar vacío."));
        }
        if (modelId == null) {
            throw new ApplicationErrorException(ApplicationError.badRequest("Debe seleccionar un modelo."));
        }

        modelProvider.findById(modelId)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Modelo no encontrado con el id: " + modelId)));

        try {
            return provider.create(StringCapitalize.capitalize(name), modelId);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationErrorException(ApplicationError.conflict("Ya existe una versión con ese nombre para este modelo."));
        }
    }

    @Override
    public Version updateVersion(Long id, String name) {
        if (name.isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El nombre no puede estar vacío."));
        }

        return provider.update(id, StringCapitalize.capitalize(name))
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Versión no encontrada con el id: " + id)));
    }

    @Override
    public List<Version> getVersionsByModel(Long modelId) {
        return provider.findByModelId(modelId);
    }

    @Override
    public Map<Long, List<Version>> getVersionsByModels(List<Long> modelIds) {
        return provider.findByModelIdIn(modelIds)
                .stream()
                .collect(Collectors.groupingBy(v -> v.getModel().getId()));
    }

    @Override
    public void deleteVersion(Long id) {
        provider.findById(id)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Versión no encontrada con el id: " + id)));
        provider.deleteById(id);
        log.info("Versión eliminada con el id: {}", id);
    }
}

package com.arielzarate.GarageManagementSystem.infraestructure.adapters;

import com.arielzarate.GarageManagementSystem.domain.model.Version;
import com.arielzarate.GarageManagementSystem.domain.ports.out.VersionProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.VersionMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.ModelEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.ModelRepository;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.VersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VersionAdapter implements VersionProvider {

    private final VersionRepository repository;
    private final ModelRepository modelRepository;
    private final VersionMapper mapper;

    @Override
    public Version create(String name, Long modelId) {
        ModelEntity model = modelRepository.findById(modelId).get();
        var entity = mapper.toEntity(name, model);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Version> update(Long id, String name) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(name);
                    return mapper.toDomain(repository.save(entity));
                });
    }

    @Override
    public Optional<Version> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Version> findByModelId(Long modelId) {
        return repository.findByModelId(modelId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Version> findByModelIdIn(List<Long> modelIds) {
        return repository.findByModelIdIn(modelIds)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

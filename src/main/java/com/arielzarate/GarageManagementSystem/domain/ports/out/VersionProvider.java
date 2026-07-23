package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Version;

import java.util.List;
import java.util.Optional;

public interface VersionProvider {

    Version create(String name, Long modelId);

    Optional<Version> update(Long id, String name);

    Optional<Version> findById(Long id);

    List<Version> findByModelId(Long modelId);

    List<Version> findByModelIdIn(List<Long> modelIds);

    void deleteById(Long id);
}

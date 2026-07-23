package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionRepository extends JpaRepository<VersionEntity, Long> {

    List<VersionEntity> findByModelId(Long modelId);

    List<VersionEntity> findByModelIdIn(List<Long> modelIds);
}

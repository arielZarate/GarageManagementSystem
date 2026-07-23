package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Version;

import java.util.List;

public interface VersionService {

    Version addVersion(String name, Long modelId);

    Version updateVersion(Long id, String name);

    List<Version> getVersionsByModel(Long modelId);

    void deleteVersion(Long id);
}

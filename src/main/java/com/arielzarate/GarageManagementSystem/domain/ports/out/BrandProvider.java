package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandProvider {

    Brand create(String name);

    Brand update(Long id, String name);

    Optional<Brand> findById(Long id);

    List<Brand> findAll();

    List<Brand> searchByName(String query);

    void deleteById(Long id);
}

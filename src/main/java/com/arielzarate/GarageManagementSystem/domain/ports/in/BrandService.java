package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;

import java.util.List;

public interface BrandService {

    Brand addBrand(String name);

    List<Brand> getBrands(String query);

    Brand updateBrand(Long id, String name);

    void deleteBrand(Long id);
}

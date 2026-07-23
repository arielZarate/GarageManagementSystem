package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    Brand addBrand(String name);

    List<Brand> getBrands(String query);

    Map<Long, Boolean> getBrandsWithModelsStatus(String query);

    Brand updateBrand(Long id, String name);

    void deleteBrand(Long id);
}

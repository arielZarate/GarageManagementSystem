package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;
import com.arielzarate.GarageManagementSystem.domain.ports.in.BrandService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.BrandProvider;
import com.arielzarate.GarageManagementSystem.domain.services.StringCapitalize;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BrandUseCase implements BrandService {

    private final BrandProvider provider;

    @Override
    public Brand addBrand(String name) {
        if(name.isBlank()) {
            throw new RuntimeException("Invalid name provided is empty");
        }
        return provider.create(StringCapitalize.capitalize(name));
    }

    @Override
    public List<Brand> getBrands(String query) {
        List<Brand> brands;
        if (query != null && !query.isBlank()) {
            brands = provider.searchByName(query.trim());
        } else {
            brands = provider.findAll();
        }
        brands.forEach(b -> b.setName(b.getName().toUpperCase()));
        return brands;
    }

    @Override
    public Brand updateBrand(Long id, String name) {
        if(name.isBlank()) {
            throw new RuntimeException("Invalid name provided is empty");
        }
        return provider.update(id, StringCapitalize.capitalize(name));
    }

    @Override
    public void deleteBrand(Long id) {
        provider.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        provider.deleteById(id);
        log.info("Brand deleted with id: {}", id);
    }
}

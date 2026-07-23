package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;
import com.arielzarate.GarageManagementSystem.domain.ports.in.BrandService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.BrandProvider;
import com.arielzarate.GarageManagementSystem.domain.services.StringCapitalize;
import com.arielzarate.GarageManagementSystem.interfaces.errors.exceptions.ApplicationErrorException;
import com.arielzarate.GarageManagementSystem.interfaces.errors.model.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
            throw new ApplicationErrorException(ApplicationError.badRequest("El campo no puede estar vacio."));
        }
        try {
            return provider.create(StringCapitalize.capitalize(name));
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationErrorException(ApplicationError.conflict("Ya existe una marca con el nombre: " + name));
        }
    }

    @Override
    public List<Brand> getBrands(String query) {
        List<Brand> brands;
        if (query != null && !query.isBlank()) {
            brands = provider.searchByName(query.trim());
        } else {
            brands = provider.findAll();
        }
        return brands;
    }

    @Override
    public Brand updateBrand(Long id, String name) {
        if(name.isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El campo no puede estar vacio."));
        }

        return provider.update(id, StringCapitalize.capitalize(name))
                .orElseThrow(()-> new ApplicationErrorException(ApplicationError.notFoundError("Marca no encontrada con el id: "+ id)) );
    }

    @Override
    public void deleteBrand(Long id) {
        provider.findById(id)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Marca no encontrada con el id " + id)));
        provider.deleteById(id);
        log.info("Marca eliminada con el id: {}", id);
    }
}

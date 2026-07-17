package com.arielzarate.GarageManagementSystem.infraestructure.adapters;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;
import com.arielzarate.GarageManagementSystem.domain.ports.out.BrandProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.BrandMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.BrandEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BrandAdapter implements BrandProvider {

    private final BrandRepository repository;
    private final BrandMapper mapper;

    @Override
    public Brand create(String name) {
        BrandEntity entity = mapper.toEntity(name);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Brand update(Long id, String name) {
        BrandEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        entity.setName(name);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Brand> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Brand> searchByName(String query) {
        return repository.findAll()
                .stream()
                .filter(b -> b.getName().toLowerCase().contains(query.toLowerCase()))
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

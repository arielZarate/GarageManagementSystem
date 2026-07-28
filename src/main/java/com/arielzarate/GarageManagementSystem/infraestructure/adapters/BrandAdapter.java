package com.arielzarate.GarageManagementSystem.infraestructure.adapters;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;
import com.arielzarate.GarageManagementSystem.domain.ports.out.BrandProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.BrandMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.BrandEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.BrandRepository;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BrandAdapter implements BrandProvider {

    private final BrandRepository repository;
    private final BrandMapper mapper;
    private final ModelRepository modelRepository;  // ← nuevo

    @Override
    public Brand create(String name) {
        BrandEntity entity = mapper.toEntity(name);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Brand> update(Long id, String name) {
         return repository.findById(id)
                         .map(
                                 entity-> {
                                     entity.setName(name);
                                     return mapper.toDomain(repository.save(entity));
                                 });

    }

    @Override
    public Optional<Brand> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Brand> findAll() {
       return enrichWithHasModels(repository.findAll());
    }

    @Override
    public List<Brand> searchByName(String query) {
       List<BrandEntity> listered= repository.findAll()
                .stream()
                .filter(b -> b.getName().toLowerCase().contains(query.toLowerCase()))
                .toList();
       
       return enrichWithHasModels(listered);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }



    // method private
    private List<Brand> enrichWithHasModels(List<BrandEntity> entities) {

        //1. get count Model by brand
        //    {1=3, 2=0, 5=7}
        Map<Long, Long> counts = modelRepository.countModelsGroupedByBrand()
                .stream()
                .collect(Collectors.toMap(
                        row -> (Long) row[0],
                        row -> (Long) row[1]
                ));
        // 2. get brands andd asign hasModels
        return entities.stream()
                .map(entity -> {
                    Brand brand = mapper.toDomain(entity);
                    brand.setHasModels(counts.getOrDefault(brand.getId(), 0L) > 0);
                    return brand;
                })
                .toList();
    }
}
